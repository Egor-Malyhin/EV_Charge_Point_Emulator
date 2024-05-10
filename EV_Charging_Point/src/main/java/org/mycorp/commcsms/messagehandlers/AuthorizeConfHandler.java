package org.mycorp.commcsms.messagehandlers;

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.core.AuthorizationStatus;
import eu.chargetime.ocpp.model.core.AuthorizeConfirmation;
import org.mycorp.models.events.commcsms.EVAuthorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeConfHandler extends OCPPConfirmationHandlerImpl {
    @Override
    public void handleConfirmation(Confirmation confirmation) {
        AuthorizeConfirmation authorizeConfirmation = (AuthorizeConfirmation) confirmation;
        boolean isAccepted = authorizeConfirmation.getIdTagInfo().getStatus() == AuthorizationStatus.Accepted;
        applicationEventPublisher.publishEvent(new EVAuthorized(this, isAccepted));
    }
}
