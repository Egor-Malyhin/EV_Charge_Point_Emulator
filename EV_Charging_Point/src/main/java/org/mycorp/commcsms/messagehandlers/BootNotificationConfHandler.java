package org.mycorp.commcsms.messagehandlers;

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.core.BootNotificationConfirmation;
import org.mycorp.models.StationVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class BootNotificationConfHandler extends OCPPConfirmationHandlerImpl {
    @Autowired
    protected BootNotificationConfHandler(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void handleConfirmation(Confirmation confirmation) {
        BootNotificationConfirmation bootNotificationConfirmation = (BootNotificationConfirmation) confirmation;
        StationVariables.getInstance().setZoneId(bootNotificationConfirmation.getCurrentTime().getZone());
    }
}
