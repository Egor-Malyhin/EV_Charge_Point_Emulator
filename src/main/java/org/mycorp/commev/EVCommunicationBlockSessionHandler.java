package org.mycorp.commev;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.mycorp.commev.evactionlisteners.EVActionListener;
import org.mycorp.commev.messagefactory.V2GMessageResFactory;
import org.mycorp.commev.messagehandlers.V2GMessageHandler;
import org.mycorp.commev.messagehandlers.V2GMessageHandlerContext;
import org.mycorp.models.messages.v2g.V2GBodyAbstractType;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.ConnectException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class EVCommunicationBlockSessionHandler extends IoHandlerAdapter implements EVCommunicationBlockInterface {
    private final V2GMessageHandlerContext v2GMessageHandlerContext;
    private final EVActionListener evActionListener;
    private final AtomicBoolean isAvailable;
    private final AtomicBoolean isDisconnectionAccept;
    private IoSession session;

    @Autowired
    public EVCommunicationBlockSessionHandler(V2GMessageHandlerContext v2GMessageHandlerContext, EVActionListener evActionListener) {
        this.v2GMessageHandlerContext = v2GMessageHandlerContext;
        this.evActionListener = evActionListener;
        this.isAvailable = new AtomicBoolean(true);
        this.isDisconnectionAccept = new AtomicBoolean();
        this.session = null;
    }

    @Override
    public void sessionOpened(IoSession sessionReceived) {
        if (!isAvailable.get())
            sessionReceived.closeNow();
        else {
            isAvailable.set(false);
            session = sessionReceived;
            V2GSessionIdCounter.getInstance().incrementCounter();
            isDisconnectionAccept.set(false);
            evActionListener.evConnected();
        }
    }

    @Override
    public void sessionClosed(IoSession sessionClosed) {
        session = null;
        evActionListener.evDisconnected(isDisconnectionAccept.get());
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        try {
            V2GMessage convertedMessage = (V2GMessage) message;
            V2GBodyAbstractType messageBody = convertedMessage.getBody().getV2GBodyAbstractType();
            String messageName = messageBody.getClass().getSimpleName();

            if (!idSessionValidator(convertedMessage.getHeader().getSessionId(), messageName)) {
                session.write(getFaultedMessage(messageName));
                throw new ConnectException("Wrong session id");
            }

            Optional<V2GMessageHandler> v2GMessageHandler = v2GMessageHandlerContext.getMessageHandlerImpl(messageName);
            v2GMessageHandler.orElseThrow(() -> new ClassNotFoundException("Not supported v2g message handler")).handleMessage(messageBody);
        } catch (ClassNotFoundException | ConnectException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(V2GMessage v2GMessage) {
        if (session != null && session.isConnected()) {
            session.write(v2GMessage);
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        throw new RuntimeException(cause);
    }

    @Override
    public void availableConnection() {
        isAvailable.compareAndSet(false, true);
    }

    @Override
    public void acceptDisconnect() {
        isDisconnectionAccept.compareAndSet(false, true);
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
                    V2GMessageResFactory.createChargingStatusRes(ResponseCode.FAILED_UnknownSession, null, null, 0, 0);
            case "PowerDeliveryReq" ->
                    V2GMessageResFactory.createPowerDeliveryRes(ResponseCode.FAILED_UnknownSession, null);
            default -> throw new IllegalArgumentException("Wrong v2gMessageType");
        };
    }
}
