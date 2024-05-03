package org.mycorp.commev;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.mycorp.commev.evactionlisteners.EVActionListener;
import org.mycorp.commev.messagehandlers.V2GMessageHandler;
import org.mycorp.commev.messagehandlers.V2GMessageHandlerContext;
import org.mycorp.models.messages.v2g.V2GBodyAbstractType;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.mycorp.models.messages.v2g.V2GSessionIdCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        try {
            Optional<V2GMessageHandler> v2GMessageHandler = v2GMessageHandlerContext.getMessageHandlerImpl(messageBody.getClass().getSimpleName());
            v2GMessageHandler.orElseThrow(() -> new ClassNotFoundException("Not supported v2g message handler")).handleMessage(messageBody);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(V2GMessage v2GMessage) {
        if (session != null && session.isConnected())
            session.write(v2GMessage);
    }
}
