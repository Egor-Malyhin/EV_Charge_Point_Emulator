package org.mycorp.ev_communication;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
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

import static org.mycorp.models.v2g_messages.types.ChargeProgress.START;

public class EVCommunicationBlockHandler extends IoHandlerAdapter {
    private final XMLConverter xmlConverter;
    private final Sender senderEVCommunicationBlock;
    private IoSession session;
    private boolean isEvConnected;

    @Autowired
    public EVCommunicationBlockHandler(XMLConverter xmlConverter, Sender senderEVCommunicationBlock) {
        this.xmlConverter = xmlConverter;
        this.senderEVCommunicationBlock = senderEVCommunicationBlock;
        this.isEvConnected=false;
        this.session=null;
    }

    @Override
    public void sessionOpened(IoSession sessionReceived) throws Exception {
        if(isEvConnected)
            sessionReceived.closeNow();
        else {
            isEvConnected = true;
            session = sessionReceived;
            V2GSessionIdCounter.getInstance().incrementCounter();
        }
    }

    @Override
    public void sessionClosed(IoSession sessionClosed) throws Exception {
        isEvConnected=false;
        session=null;
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        if (!(message instanceof byte[] messageByte))
            throw new RuntimeException("Wrong Communication type");

        V2GMessage convertedMessage = xmlConverter.convertToObject(messageByte);

        V2GBodyAbstractType receivedMessage = convertedMessage.getBody().getV2GBodyAbstractType();

        V2GMessagesClassification v2gMessageType = findMessageType(receivedMessage);

        if(v2gMessageType==null)
            throw new RuntimeException("Wrong Message type");

        switch (v2gMessageType){
            case SESSION_SETUP_REQ:
                senderEVCommunicationBlock.sendMessage(new StartAuthorizeMessage(((SessionSetupReq) receivedMessage).getEvccId()));
                break;
            case CHARGE_PARAMETER_DISCOVERY_REQ:
                senderEVCommunicationBlock.sendMessage(new PrepareChargingMessage(((ChargeParameterDiscoveryReq) receivedMessage).getAcEvChargeParameter().geteАmount().getValue()));
                break;
            case POWER_DELIVERY_REQ:
                if(((PowerDeliveryReq) receivedMessage).getChargeProgress()==START)
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
    }

    public void sendMessage(V2GMessage v2GMessage){
        if(session!=null && session.isConnected()) {
            byte[] exiMessage = xmlConverter.convertToEXIMessage(v2GMessage);
            session.write(exiMessage);
        }
    }

    private V2GMessagesClassification findMessageType(V2GBodyAbstractType receivedMessageBodyType){
        for (V2GMessagesClassification t : V2GMessagesClassification.values()) {
            if (t.getMessageType().isInstance(receivedMessageBodyType)) {
                return t;
            }
        }
        return null;
    }
}
