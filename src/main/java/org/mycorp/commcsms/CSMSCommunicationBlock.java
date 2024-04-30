package org.mycorp.commcsms;

import eu.chargetime.ocpp.JSONClient;
import eu.chargetime.ocpp.OccurenceConstraintException;
import eu.chargetime.ocpp.UnsupportedFeatureException;
import eu.chargetime.ocpp.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


@Component
public class CSMSCommunicationBlock implements Runnable, CSMSCommunicationBlockInterface{
    private final BlockingQueue<Request> messageQueue;
    private final CSMSCommunicationBlockSessionHandler csmsCommunicationBlockSessionHandler;
    private final JSONClient jsonClient;

    @Autowired
    public CSMSCommunicationBlock(CSMSCommunicationBlockSessionHandler csmsCommunicationBlockSessionHandler, JSONClient jsonClient) {
        this.csmsCommunicationBlockSessionHandler = csmsCommunicationBlockSessionHandler;
        this.jsonClient = jsonClient;
        this.messageQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
       /* jsonClient.connect("ws//some_ws", null);
        while (true) {
            try {
                jsonClient.send(request).whenComplete((s, ex) -> {
                    context.getImpl(req).handle(req);
                });
            } catch (InterruptedException | OccurenceConstraintException | UnsupportedFeatureException e) {
                e.printStackTrace();
                //throw new RuntimeException(e);
            }
        }*/
    }

    @Override
    public void addToMessageQueue(Request ocppRequest) {
        messageQueue.add(ocppRequest);
    }
}
