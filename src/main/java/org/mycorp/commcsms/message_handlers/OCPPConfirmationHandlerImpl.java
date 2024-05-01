package org.mycorp.commcsms.message_handlers;

import eu.chargetime.ocpp.model.Confirmation;
import org.springframework.context.ApplicationEventPublisher;
public abstract class OCPPConfirmationHandlerImpl implements OCPPConfirmationHandler {
    protected final ApplicationEventPublisher ocppPublisher;

    protected OCPPConfirmationHandlerImpl(ApplicationEventPublisher ocppPublisher) {
        this.ocppPublisher = ocppPublisher;
    }
}
