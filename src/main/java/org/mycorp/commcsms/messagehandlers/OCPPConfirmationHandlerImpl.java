package org.mycorp.commcsms.messagehandlers;

import org.springframework.context.ApplicationEventPublisher;

//Extend this class for handling Confirmation from CSMS
public abstract class OCPPConfirmationHandlerImpl implements OCPPConfirmationHandler {
    protected final ApplicationEventPublisher ocppPublisher;

    protected OCPPConfirmationHandlerImpl(ApplicationEventPublisher ocppPublisher) {
        this.ocppPublisher = ocppPublisher;
    }
}
