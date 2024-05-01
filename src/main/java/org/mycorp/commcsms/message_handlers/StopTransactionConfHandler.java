package org.mycorp.commcsms.message_handlers;

import eu.chargetime.ocpp.model.Confirmation;
import org.mycorp.models.StationVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class StopTransactionConfHandler extends OCPPConfirmationHandlerImpl {
    @Autowired
    protected StopTransactionConfHandler(ApplicationEventPublisher ocppPublisher) {
        super(ocppPublisher);
    }

    //Use this method as
    @Override
    public void handleConfirmation(Confirmation confirmation) {
        StationVariables.getInstance().setTransactionId(0);
    }
}
