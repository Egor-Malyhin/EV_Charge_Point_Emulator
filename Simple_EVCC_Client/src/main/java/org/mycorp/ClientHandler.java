package org.mycorp;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.mycorp.messagefactory.V2GMessageReqFactory;
import org.mycorp.messages.V2GBodyAbstractType;
import org.mycorp.messages.V2GMessage;
import org.mycorp.messages.res.ChargingStatusRes;
import org.mycorp.messages.types.enums.ChargeProgress;
import org.mycorp.messages.types.enums.ChargingSession;
import org.mycorp.messages.types.enums.EVSENotification;
import org.mycorp.models.EVCharacteristics;
import org.mycorp.models.EVDataVariables;
import org.mycorp.models.SessionId;
import org.mycorp.models.V2GMessagesResClassification;

import java.util.Objects;

@Slf4j
public class ClientHandler extends IoHandlerAdapter {

    //I used this structure because the IoListener is not working properly,
    //and in order to close the application without throwing exceptions,
    //we need to execute the connector.dispose() method.

    //Therefore, pass a reference to the IoConnector object created in the Main class here.
    private final IoConnector connector;

    public ClientHandler(IoConnector connector) {
        this.connector = connector;
    }

    @Override
    public void sessionOpened(IoSession session) {
        V2GMessage sessionSetupReqMessage = V2GMessageReqFactory.createSessionSetupReqMessage(SessionId.getInstance().getSessionIdValue(), EVCharacteristics.evccId);
        session.write(sessionSetupReqMessage);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws InterruptedException {
        V2GMessage v2GMessage = (V2GMessage) message;
        V2GBodyAbstractType v2GMessageBody = v2GMessage.getBody().getV2GBodyAbstractType();
        V2GMessagesResClassification messageType = getMessageType(v2GMessageBody);

        switch (Objects.requireNonNull(messageType)) {
            case SESSION_SETUP_RES:
                Thread.sleep(2500);
                handleSessionSetupResponse(session, v2GMessage.getHeader().getSessionId());
                break;
            case CHARGE_PARAMETER_DISCOVERY_RES:
                Thread.sleep(2500);
                handleChargeParameterDiscoveryResponse(session);
                break;
            case POWER_DELIVERY_RES:
                Thread.sleep(2500);
                handlePowerDeliveryResponse(session);
                break;
            case CHARGING_STATUS_RES:
                Thread.sleep(2500);
                handleChargingStatusResponse(session, v2GMessageBody);
                break;
            case SESSION_STOP_RES:
                session.closeNow();
                break;
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        log.warn("Exception caught: ", cause);
    }

    //When the session is closed, the client also closes
    @Override
    public void sessionClosed(IoSession session) {
        connector.dispose();
        System.exit(0);
    }

    private void handleSessionSetupResponse(IoSession session, byte[] sessionId) {
        SessionId.getInstance().setSessionIdValue(sessionId);
        EVDataVariables evDataVariables = EVDataVariables.getInstance();
        V2GMessage chargeParameterDiscoveryReqMessage = V2GMessageReqFactory.createChargeParameterDiscoveryReqMessage(sessionId, evDataVariables.getRequestedEnergyTransferMode(), evDataVariables.getEAmount(), EVCharacteristics.evMaxVoltage, EVCharacteristics.evMaxCurrent, EVCharacteristics.evMinCurrent);
        session.write(chargeParameterDiscoveryReqMessage);
    }

    private void handleChargeParameterDiscoveryResponse(IoSession session) {
        V2GMessage powerDeliveryReqMessage = V2GMessageReqFactory.createPowerDeliveryReq(SessionId.getInstance().getSessionIdValue(), ChargeProgress.START);
        session.write(powerDeliveryReqMessage);
    }

    private void handlePowerDeliveryResponse(IoSession session) throws InterruptedException {
        log.info("Charging Start");
        sendChargingStatusReq(session);
    }

    private void handleChargingStatusResponse(IoSession session, V2GBodyAbstractType v2GMessageBody) throws InterruptedException {
        ChargingStatusRes chargingStatusResMessageBody = (ChargingStatusRes) v2GMessageBody;
        log.info("Meter Id: " + chargingStatusResMessageBody.getMeterInfo().getMeterId() + ", current meter reading: " + chargingStatusResMessageBody.getMeterInfo().getMeterReading() + " kWh");
        if (chargingStatusResMessageBody.getAc_evseStatus().getEvseNotification() == EVSENotification.NONE) {
            sendChargingStatusReq(session);
        } else {
            log.info("Charging stopped");
            V2GMessage stopSessionReqMessage = V2GMessageReqFactory.createSessionStopReqMessage(SessionId.getInstance().getSessionIdValue(), ChargingSession.TERMINATE);
            session.write(stopSessionReqMessage);
        }
    }

    private void sendChargingStatusReq(IoSession session) throws InterruptedException {
        V2GMessage chargingStatusReqMessage = V2GMessageReqFactory.createChargingStatusReq(SessionId.getInstance().getSessionIdValue());
        session.write(chargingStatusReqMessage);
    }

    private V2GMessagesResClassification getMessageType(V2GBodyAbstractType v2GMessageBody) {
        for (V2GMessagesResClassification t : V2GMessagesResClassification.values())
            if (t.getMessageType() == v2GMessageBody.getClass())
                return t;
        return null;
    }
}
