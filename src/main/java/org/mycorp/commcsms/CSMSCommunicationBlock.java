package org.mycorp.commcsms;

import org.mycorp.models.messages.ocpp.OCPPMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;


@Component
public class CSMSCommunicationBlock implements Runnable, CSMSCommunicationBlockInterface{
    private final BlockingQueue<OCPPMessage> messageQueue;
    private final CSMSCommunicationBlockSessionHandler csmsCommunicationBlockSessionHandler;

    @Autowired
    public CSMSCommunicationBlock(CSMSCommunicationBlockSessionHandler csmsCommunicationBlockSessionHandler) {
        this.csmsCommunicationBlockSessionHandler = csmsCommunicationBlockSessionHandler;
        this.messageQueue = new PriorityBlockingQueue<>();
    }

    @Override
    public void run() {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.connect("ws//some_ws", csmsCommunicationBlockSessionHandler);

        while (true) {
            try {
                csmsCommunicationBlockSessionHandler.sendMessage(messageQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addToMessageQueue(OCPPMessage ocppMessage) {
        messageQueue.add(ocppMessage);
    }
}
