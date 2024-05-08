package org.mycorp.commcsms;

import eu.chargetime.ocpp.JSONClient;
import eu.chargetime.ocpp.OccurenceConstraintException;
import eu.chargetime.ocpp.UnsupportedFeatureException;
import eu.chargetime.ocpp.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.mycorp.commcsms.messagehandlers.OCPPConfirmationHandler;
import org.mycorp.commcsms.messagehandlers.OCPPConfirmationHandlerContext;
import org.mycorp.commcsms.websocketactionlistener.WebSocketActionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Component
public class CSMSCommunicationBlock implements Runnable, CSMSCommunicationBlockInterface {
    private final BlockingQueue<Request> messageQueue;
    private final OCPPConfirmationHandlerContext ocppConfirmationHandlerContext;
    private final JSONClient jsonClient;
    private final WebSocketActionListener webSocketActionListener;
    @Value("${commcsms.connect.ws}")
    private String websocketServerWS;

    @Autowired
    public CSMSCommunicationBlock(OCPPConfirmationHandlerContext ocppConfirmationHandlerContext, JSONClient jsonClient, WebSocketActionListener webSocketActionListener) {
        this.ocppConfirmationHandlerContext = ocppConfirmationHandlerContext;
        this.jsonClient = jsonClient;
        this.webSocketActionListener = webSocketActionListener;
        this.messageQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        connectToCSMS();
        while (true) {
            try {
                Request request = messageQueue.take();
                log.info("Send Request to CSMS: " + request.getClass().getSimpleName());
                jsonClient.send(request).whenComplete((s, ex) -> {
                    try {
                        log.info("Received Confirmation from CSMS: " + s.getClass().getSimpleName());
                        Optional<OCPPConfirmationHandler> ocppConfirmationHandler = ocppConfirmationHandlerContext.getOCPPConfirmationHandlerImpl(s.getClass().getSimpleName());
                        ocppConfirmationHandler.orElseThrow(() -> new ClassNotFoundException("Not supported confirmation handler")).handleConfirmation(s);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (InterruptedException | OccurenceConstraintException | UnsupportedFeatureException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addToMessageQueue(Request ocppRequest) {
        messageQueue.add(ocppRequest);
    }

    @Override
    public void connectToCSMS() {
        jsonClient.connect(websocketServerWS, webSocketActionListener);
    }
}
