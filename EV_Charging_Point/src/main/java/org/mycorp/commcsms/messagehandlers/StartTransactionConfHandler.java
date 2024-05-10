package org.mycorp.commcsms.messagehandlers;

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.core.AuthorizationStatus;
import eu.chargetime.ocpp.model.core.StartTransactionConfirmation;
import org.mycorp.models.StationVariables;
import org.mycorp.models.events.commcsms.CSMSChargingAccept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StartTransactionConfHandler extends OCPPConfirmationHandlerImpl {
    @Override
    public void handleConfirmation(Confirmation confirmation) {
        StartTransactionConfirmation startTransactionConfirmation = (StartTransactionConfirmation) confirmation;
        StationVariables.getInstance().setTransactionId(startTransactionConfirmation.getTransactionId());
        boolean isAccepted = startTransactionConfirmation.getIdTagInfo().getStatus() == AuthorizationStatus.Accepted;
        applicationEventPublisher.publishEvent(new CSMSChargingAccept(this, isAccepted));
    }
}
