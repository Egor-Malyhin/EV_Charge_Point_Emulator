package org.mycorp.commcsms.message_handlers;

import eu.chargetime.ocpp.model.Confirmation;
import org.springframework.context.ApplicationEventPublisher;

public abstract class CoreProfileOCPPConfirmationHandler implements OCPPConfirmationHandler {
    protected final ApplicationEventPublisher ocppPublisher;

    public CoreProfileOCPPConfirmationHandler(ApplicationEventPublisher ocppPublisher) {
        this.ocppPublisher = ocppPublisher;
    }
}
