package org.mycorp.ev_communication.message_handlers;

import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public abstract class V2GMessageHandlerImpl implements V2GMessageHandler {
    protected final ApplicationEventPublisher applicationEventPublisher;

    protected V2GMessageHandlerImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
