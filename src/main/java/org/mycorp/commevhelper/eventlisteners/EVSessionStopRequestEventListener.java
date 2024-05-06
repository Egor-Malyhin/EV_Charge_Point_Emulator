package org.mycorp.commevhelper.eventlisteners;

import org.mycorp.commevhelper.EVCommunicationBlockHelper;
import org.mycorp.commevhelper.EVCommunicationBlockHelperInterface;
import org.mycorp.models.events.commev.EVSessionStopRequest;
import org.mycorp.models.events.commevhelper.EVDisconnectionAccept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EVSessionStopRequestEventListener extends EVCommunicationBlockHelperEventListener<EVSessionStopRequest> {
    @Autowired
    protected EVSessionStopRequestEventListener(EVCommunicationBlockHelperInterface evCommunicationBlockHelperInterface) {
        super(evCommunicationBlockHelperInterface);
    }

    @Override
    @EventListener
    public void listenEvent(EVSessionStopRequest stationEvent) {
        evCommunicationBlockHelperInterface.acceptSessionClose();
    }
}
