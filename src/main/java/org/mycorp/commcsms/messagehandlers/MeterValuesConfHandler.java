package org.mycorp.commcsms.messagehandlers;

import eu.chargetime.ocpp.model.Confirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MeterValuesConfHandler extends OCPPConfirmationHandlerImpl {
    @Autowired
    protected MeterValuesConfHandler(ApplicationEventPublisher ocppPublisher) {
        super(ocppPublisher);
    }

    //Left this method empty so that an exception would not be thrown,
    //since the protocol does not provide for processing this message.
    @Override
    public void handleConfirmation(Confirmation confirmation) {
    }
}
