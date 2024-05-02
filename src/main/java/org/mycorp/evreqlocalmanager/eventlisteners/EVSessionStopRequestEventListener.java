package org.mycorp.evreqlocalmanager.eventlisteners;

import org.mycorp.evreqlocalmanager.EVLocalRequestManager;
import org.mycorp.models.events.commev.EVSessionStopRequest;
import org.mycorp.models.events.evreqlocalmanager.EVDisconnectionAccept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EVSessionStopRequestEventListener extends EVLocalRequestManager<EVSessionStopRequest> {
    @Autowired
    protected EVSessionStopRequestEventListener(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    @EventListener
    public void listenEvent(EVSessionStopRequest stationEvent) {
        applicationEventPublisher.publishEvent(new EVDisconnectionAccept());
    }
}
