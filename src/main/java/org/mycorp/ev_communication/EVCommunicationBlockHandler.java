package org.mycorp.ev_communication;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.mycorp.charge_control.ChargeControlInterfaceEV;
import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.V2GMessage;
import org.mycorp.models.v2g_messages.V2GMessagesClassification;
import org.mycorp.models.v2g_messages.V2GSessionIdCounter;

public class EVCommunicationBlockHandler extends IoHandlerAdapter {

    private final XMLConverter xmlConverter;
    private ChargeControlInterfaceEV chargeControlInterfaceEV;

    private IoSession session;

    private boolean isEvConnected;

    public EVCommunicationBlockHandler(XMLConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
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

        V2GMessagesClassification v2gMessageType = null;
        for (V2GMessagesClassification t : V2GMessagesClassification.values()) {
            if (t.getMessageType().isInstance(receivedMessage)) {
                v2gMessageType = t;
                break;
            }
        }

        if(v2gMessageType==null)
            throw new RuntimeException("Wrong Message type");

        switch (v2gMessageType){
            case SESSION_SETUP_REQ:
                chargeControlInterfaceEV.startAuthorize(receivedMessage.getEVID());
                break;
            case CHARGE_PARAMETER_DISCOVERY_REQ:
                chargeControlInterfaceEV.prepareCharging(receivedMessage.getEamount());
                break;
            case POWER_DELIVERY_REQ:
                chargeControlInterfaceEV.startChargingRequest();
                break;
            case CHARGING_STATUS_REQ:
                chargeControlInterfaceEV.getChargingStatus();
                break;
            case SESSION_STOP_RES:
                chargeControlInterfaceEV.stopChargingRequest();
        }
    }

    public void sendMessage(V2GMessage v2GMessage){
        if(session!=null && session.isConnected()) {
            byte[] exiMessage = xmlConverter.convertToEXIMessage(v2GMessage);
            session.write(exiMessage);
        }
    }
}
