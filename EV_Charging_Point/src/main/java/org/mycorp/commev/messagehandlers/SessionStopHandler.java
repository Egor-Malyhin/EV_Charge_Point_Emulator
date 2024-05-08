package org.mycorp.commev.messagehandlers;

import org.mycorp.messages.V2GBodyAbstractType;
import org.mycorp.models.events.commev.EVSessionStopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SessionStopHandler extends V2GMessageHandlerImpl {
    @Autowired
    protected SessionStopHandler(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void handleMessage(V2GBodyAbstractType v2gMessageBody) {
        applicationEventPublisher.publishEvent(new EVSessionStopRequest(this));
    }
}
