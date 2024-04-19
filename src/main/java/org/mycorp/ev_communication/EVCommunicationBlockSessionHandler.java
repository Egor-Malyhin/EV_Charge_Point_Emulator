package org.mycorp.ev_communication;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.mycorp.ev_communication.message_handlers.*;
import org.mycorp.ev_communication.protocol_filter.XMLConverter;
import org.mycorp.mediators.senders.Sender;
import org.mycorp.models.station_messages.control_system_messages_ev_comm.*;
import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.V2GMessage;
import org.mycorp.models.v2g_messages.V2GMessagesClassification;
import org.mycorp.models.v2g_messages.V2GSessionIdCounter;
import org.mycorp.models.v2g_messages.req.ChargeParameterDiscoveryReq;
import org.mycorp.models.v2g_messages.req.PowerDeliveryReq;
import org.mycorp.models.v2g_messages.req.SessionSetupReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.soap.SOAPMessage;

import static org.mycorp.models.v2g_messages.types.ChargeProgress.START;

@Component
public class EVCommunicationBlockSessionHandler extends IoHandlerAdapter {
    private final V2GMessageHandlerContext v2GMessageHandlerContext;
    private IoSession session;
    private boolean isEvConnected;

    @Autowired
    public EVCommunicationBlockSessionHandler(V2GMessageHandlerContext v2GMessageHandlerContext) {
        this.v2GMessageHandlerContext = v2GMessageHandlerContext;
        this.isEvConnected = false;
        this.session = null;
    }

    @Override
    public void sessionOpened(IoSession sessionReceived) throws Exception {
        if (isEvConnected)
            sessionReceived.closeNow();
        else {
            isEvConnected = true;
            session = sessionReceived;
            V2GSessionIdCounter.getInstance().incrementCounter();
        }
    }

    @Override
    public void sessionClosed(IoSession sessionClosed) throws Exception {
        isEvConnected = false;
        session = null;
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        V2GMessage convertedMessage = (V2GMessage) message;
        V2GBodyAbstractType messageBody = convertedMessage.getBody().getV2GBodyAbstractType();
        V2GMessageHandler v2gMessageHandler = v2GMessageHandlerContext.getMessageHandlerImpl(messageBody);

        v2gMessageHandler.handleMessage(messageBody);
    }
/*
        if (v2gMessageType == null)
            throw new RuntimeException("Wrong Message type");

        switch (v2gMessageType) {
            case SESSION_SETUP_REQ:
                senderEVCommunicationBlock.sendMessage(new StartAuthorizeMessage(((SessionSetupReq) receivedMessage).getEvccId()));
                break;
            case CHARGE_PARAMETER_DISCOVERY_REQ:
                senderEVCommunicationBlock.sendMessage(new PrepareChargingMessage(((ChargeParameterDiscoveryReq) receivedMessage).getAcEvChargeParameter().geteАmount().getValue()));
                break;
            case POWER_DELIVERY_REQ:
                if (((PowerDeliveryReq) receivedMessage).getChargeProgress() == START)
                    senderEVCommunicationBlock.sendMessage(new StartChargingRequestMessage());
                else
                    senderEVCommunicationBlock.sendMessage(new StopChargingRequestMessage());
                break;
            case CHARGING_STATUS_REQ:
                senderEVCommunicationBlock.sendMessage(new GetChargingStatusMessage());
                break;
            case SESSION_STOP_REQ:
                senderEVCommunicationBlock.sendMessage(new CloseSessionRequestMessage());
        }
    }*/

    public void sendMessage(V2GMessage v2GMessage) {
        if (session != null && session.isConnected())
            session.write(v2GMessage);
    }

}
