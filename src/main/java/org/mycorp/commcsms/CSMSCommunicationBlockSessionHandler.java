package org.mycorp.commcsms;

import org.mycorp.commcsms.message_handlers.OCPPMessageHandler;
import org.mycorp.commcsms.message_handlers.OCPPMessageHandlerContext;
import org.mycorp.models.messages.ocpp.OCPPMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

@Component
public class CSMSCommunicationBlockSessionHandler extends StompSessionHandlerAdapter {
    private final OCPPMessageHandlerContext ocppMessageHandlerContext;
    private StompSession stompSession;

    @Autowired
    public CSMSCommunicationBlockSessionHandler(OCPPMessageHandlerContext ocppMessageHandlerContext) {
        this.ocppMessageHandlerContext = ocppMessageHandlerContext;
        stompSession = null;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/messages", this);
        stompSession = session;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        try {
            OCPPMessage ocppMessage = (OCPPMessage) payload;
            OCPPMessageHandler messageHandler = ocppMessageHandlerContext.getOCPPMessageOperatorImpl(ocppMessage);
            messageHandler.handleMessage(ocppMessage);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(OCPPMessage ocppMessage) {
        if (stompSession != null && stompSession.isConnected())
            stompSession.send("/requests", ocppMessage);
    }
}
