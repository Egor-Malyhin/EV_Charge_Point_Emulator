package org.mycorp.commcsms.websocketactionlistener;

import eu.chargetime.ocpp.ClientEvents;
import org.mycorp.models.events.commcsms.CSMSDisconnect;
import org.mycorp.models.events.commcsms.ClientConnected;
import org.mycorp.stationeventpublisher.StationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class WebSocketActionListener extends StationEventPublisher implements ClientEvents {
    private int connectionTryingCounter;

    protected WebSocketActionListener() {
        this.connectionTryingCounter = 0;
    }

    @Override
    public void connectionOpened() {
        applicationEventPublisher.publishEvent(new ClientConnected(this));
    }

    @Override
    public void connectionClosed() {
        if (connectionTryingCounter < 3) {
            try {
                Thread.sleep(3000);
                connectionTryingCounter++;
                applicationEventPublisher.publishEvent(new CSMSDisconnect(this));
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        } else
            connectionTryingCounter = 0;
    }
}