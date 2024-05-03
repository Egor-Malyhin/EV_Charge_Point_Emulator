package org.mycorp.commcsms;

import eu.chargetime.ocpp.JSONClient;
import eu.chargetime.ocpp.OccurenceConstraintException;
import eu.chargetime.ocpp.UnsupportedFeatureException;
import eu.chargetime.ocpp.model.Request;
import org.mycorp.commcsms.messagehandlers.OCPPConfirmationHandler;
import org.mycorp.commcsms.messagehandlers.OCPPConfirmationHandlerContext;
import org.mycorp.commcsms.websocketactionlistener.WebSocketActionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


@Component
public class CSMSCommunicationBlock implements Runnable, CSMSCommunicationBlockInterface {
    private final BlockingQueue<Request> messageQueue;
    private final OCPPConfirmationHandlerContext ocppConfirmationHandlerContext;
    private final JSONClient jsonClient;
    private final String websocketServerWS;
    private final WebSocketActionListener webSocketActionListener;

    @Autowired
    public CSMSCommunicationBlock(OCPPConfirmationHandlerContext ocppConfirmationHandlerContext, JSONClient jsonClient, String websocketServerWS, WebSocketActionListener webSocketActionListener) {
        this.ocppConfirmationHandlerContext = ocppConfirmationHandlerContext;
        this.jsonClient = jsonClient;
        this.websocketServerWS = websocketServerWS;
        this.webSocketActionListener = webSocketActionListener;
        this.messageQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        jsonClient.connect(websocketServerWS, null);
        webSocketActionListener.clientConnected();
        while (true) {
            try {
                Request request = messageQueue.take();
                jsonClient.send(request).whenComplete((s, ex) -> {
                    try {
                        Optional<OCPPConfirmationHandler> ocppConfirmationHandler = ocppConfirmationHandlerContext.getOCPPConfirmationHandlerImpl(s.getClass().getSimpleName());
                        ocppConfirmationHandler.orElseThrow(() -> new ClassNotFoundException("Not supported confirmation handler")).handleConfirmation(s);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException | OccurenceConstraintException | UnsupportedFeatureException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addToMessageQueue(Request ocppRequest) {
        messageQueue.add(ocppRequest);
    }
}
