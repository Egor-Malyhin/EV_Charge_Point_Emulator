package org.mycorp.commcsms.messagehandlers;

import eu.chargetime.ocpp.model.Confirmation;

public interface OCPPConfirmationHandler {
    void handleConfirmation(Confirmation confirmation);
}