package org.mycorp.commev;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.mycorp.commev.evactionlisteners.EVActionListener;
import org.mycorp.commev.messagefactory.V2GMessageResFactory;
import org.mycorp.commev.messagehandlers.V2GMessageHandler;
import org.mycorp.commev.messagehandlers.V2GMessageHandlerContext;
import org.mycorp.models.messages.v2g.V2GBodyAbstractType;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.mycorp.models.messages.v2g.types.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class EVCommunicationBlockSessionHandler extends IoHandlerAdapter implements EVCommunicationBlockInterface {
    private final V2GMessageHandlerContext v2GMessageHandlerContext;
    private final EVActionListener evActionListener;
    private IoSession session;
    private boolean isEvConnected;

    @Autowired
    public EVCommunicationBlockSessionHandler(V2GMessageHandlerContext v2GMessageHandlerContext, EVActionListener evActionListener) {
        this.v2GMessageHandlerContext = v2GMessageHandlerContext;
        this.evActionListener = evActionListener;
        this.isEvConnected = false;
        this.session = null;
    }

    @Override
    public void sessionOpened(IoSession sessionReceived) {
        if (isEvConnected)
            sessionReceived.closeNow();
        else {
            isEvConnected = true;
            session = sessionReceived;
            V2GSessionIdCounter.getInstance().incrementCounter();
            evActionListener.evConnected();
        }
    }

    @Override
    public void sessionClosed(IoSession sessionClosed) {
        isEvConnected = false;
        session = null;
        evActionListener.evDisconnected();
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        V2GMessage convertedMessage = (V2GMessage) message;
        V2GBodyAbstractType messageBody = convertedMessage.getBody().getV2GBodyAbstractType();

        if (!idSessionValidator(convertedMessage.getHeader().getSessionId(), messageBody.getClass().getSimpleName()))
            session.write(getFaultedMessage(messageBody.getClass().getSimpleName()));

        try {
            Optional<V2GMessageHandler> v2GMessageHandler = v2GMessageHandlerContext.getMessageHandlerImpl(messageBody.getClass().getSimpleName());
            v2GMessageHandler.orElseThrow(() -> new ClassNotFoundException("Not supported v2g message handler")).handleMessage(messageBody);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(V2GMessage v2GMessage) {
        if (session != null && session.isConnected())
            session.write(v2GMessage);
    }

    private boolean idSessionValidator(byte[] sessionId, String messageType) {
        return messageType.equals("SessionSetupReq") || Arrays.equals(sessionId, V2GSessionIdCounter.getInstance().getSessionId());
    }

    private V2GMessage getFaultedMessage(String messageType) {
        return switch (messageType) {
            case "SessionStopReq" ->
                    V2GMessageResFactory.createSessionStopResMessage(ResponseCode.FAILED_UnknownSession);
            case "ChargeParameterDiscoveryReq" ->
                    V2GMessageResFactory.createChargeParameterDiscoveryResMessage(ResponseCode.FAILED_UnknownSession, 0, 0);
            case "ChargingStatusReq" ->
                    V2GMessageResFactory.createChargingStatusRes(ResponseCode.FAILED_UnknownSession, null, null);
            case "PowerDeliveryReq" ->
                    V2GMessageResFactory.createPowerDeliveryRes(ResponseCode.FAILED_UnknownSession, null);
            default -> throw new IllegalArgumentException("Wrong v2gMessageType");
        };
    }
}
