package org.mycorp.commev.messagehandlers;

import org.springframework.context.ApplicationEventPublisher;

public abstract class V2GMessageHandlerImpl implements V2GMessageHandler {
    protected final ApplicationEventPublisher applicationEventPublisher;

    protected V2GMessageHandlerImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
