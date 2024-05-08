package org.mycorp.commcsms.messagehandlers;

import eu.chargetime.ocpp.model.Confirmation;
import org.mycorp.models.StationVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StopTransactionConfHandler extends OCPPConfirmationHandlerImpl {
    @Autowired
    protected StopTransactionConfHandler(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void handleConfirmation(Confirmation confirmation) {
        StationVariables.getInstance().setTransactionId(0);
    }
}
