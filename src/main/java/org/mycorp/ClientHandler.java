package org.mycorp;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.mycorp.messagefactory.V2GMessageReqFactory;
import org.mycorp.models.EVCharacteristics;
import org.mycorp.models.EVDataVariables;
import org.mycorp.models.SessionId;
import org.mycorp.v2g.*;
import org.mycorp.v2g.res.ChargingStatusRes;
import org.mycorp.v2g.types.*;

import java.util.Objects;

@Slf4j
public class ClientHandler extends IoHandlerAdapter {

    @Override
    public void sessionOpened(IoSession session) {
        V2GMessage sessionSetupReqMessage = V2GMessageReqFactory.createSessionSetupReqMessage(EVCharacteristics.evccId);
        session.write(sessionSetupReqMessage);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws InterruptedException {
        V2GMessage v2GMessage = (V2GMessage) message;
        V2GBodyAbstractType v2GMessageBody = v2GMessage.getBody().getV2GBodyAbstractType();
        V2GMessagesClassification messageType = getMessageType(v2GMessageBody);

        switch (Objects.requireNonNull(messageType)) {
            case SESSION_SETUP_RES:
                handleSessionSetupResponse(session, v2GMessage.getHeader().getSessionId());
                break;
            case CHARGE_PARAMETER_DISCOVERY_RES:
                handleChargeParameterDiscoveryResponse(session);
                break;
            case POWER_DELIVERY_RES:
                handlePowerDeliveryResponse(session);
                break;
            case CHARGING_STATUS_RES:
                handleChargingStatusResponse(session, v2GMessageBody);
                break;
            case SESSION_STOP_RES:
                session.closeNow();
                break;
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        cause.printStackTrace();
    }


    private void handleSessionSetupResponse(IoSession session, byte[] sessionId) {
        SessionId.getInstance().setSessionIdValue(sessionId);
        EVDataVariables evDataVariables = EVDataVariables.getInstance();
        V2GMessage chargeParameterDiscoveryReqMessage = V2GMessageReqFactory.createChargeParameterDiscoveryReqMessage(evDataVariables.getRequestedEnergyTransferMode(), evDataVariables.getEAmount(), EVCharacteristics.evMaxVoltage, EVCharacteristics.evMaxCurrent, EVCharacteristics.evMinCurrent);
        session.write(chargeParameterDiscoveryReqMessage);
    }

    private void handleChargeParameterDiscoveryResponse(IoSession session) {
        V2GMessage powerDeliveryReqMessage = V2GMessageReqFactory.createPowerDeliveryReq(ChargeProgress.START);
        session.write(powerDeliveryReqMessage);
    }

    private void handlePowerDeliveryResponse(IoSession session) throws InterruptedException {
        log.info("Charging Start");
        sendChargingStatusReq(session);
    }

    private void handleChargingStatusResponse(IoSession session, V2GBodyAbstractType v2GMessageBody) throws InterruptedException {
        ChargingStatusRes chargingStatusResMessageBody = (ChargingStatusRes) v2GMessageBody;
        if (chargingStatusResMessageBody.getAc_evseStatus().getEvseNotification() == EVSENotification.NONE) {
            log.info("Meter Id: " + chargingStatusResMessageBody.getMeterInfo().getMeterId() + ", current meter reading: " + chargingStatusResMessageBody.getMeterInfo().getMeterReading() + " kWh");
            sendChargingStatusReq(session);
        } else {
            log.info("Charging stopped");
            V2GMessage stopSessionReqMessage = V2GMessageReqFactory.createSessionStopReqMessage(ChargingSession.TERMINATE);
            session.write(stopSessionReqMessage);
        }
    }

    private void sendChargingStatusReq(IoSession session) throws InterruptedException {
        Thread.sleep(2500);
        V2GMessage chargingStatusReqMessage = V2GMessageReqFactory.createChargingStatusReq();
        session.write(chargingStatusReqMessage);
    }

    private V2GMessagesClassification getMessageType(V2GBodyAbstractType v2GMessageBody) {
        for (V2GMessagesClassification t : V2GMessagesClassification.values())
            if (t.getMessageType() == v2GMessageBody.getClass())
                return t;
        return null;
    }
}
