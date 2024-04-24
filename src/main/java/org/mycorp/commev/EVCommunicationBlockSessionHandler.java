package org.mycorp.commev;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.mycorp.commev.messagehandlers.*;
import org.mycorp.models.messages.v2g.V2GBodyAbstractType;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.mycorp.models.messages.v2g.V2GSessionIdCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EVCommunicationBlockSessionHandler extends IoHandlerAdapter implements EVCommunicationBlockInterface {
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

    @Override
    public void sendMessage(V2GMessage v2GMessage) {
        if (session != null && session.isConnected())
            session.write(v2GMessage);
    }
}
