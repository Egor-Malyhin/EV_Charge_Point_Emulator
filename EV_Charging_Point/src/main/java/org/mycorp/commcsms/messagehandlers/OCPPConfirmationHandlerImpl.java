package org.mycorp.commcsms.messagehandlers;

import org.mycorp.stationeventpublisher.StationEventPublisher;
import org.springframework.context.ApplicationEventPublisher;

//Extend this class for handling Confirmation from CSMS
public abstract class OCPPConfirmationHandlerImpl extends StationEventPublisher implements OCPPConfirmationHandler {
    protected OCPPConfirmationHandlerImpl(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }
}
