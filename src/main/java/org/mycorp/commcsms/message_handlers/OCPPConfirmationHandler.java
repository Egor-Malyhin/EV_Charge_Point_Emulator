package org.mycorp.commcsms.message_handlers;

import eu.chargetime.ocpp.model.Confirmation;

public interface OCPPConfirmationHandler {
    void handleConfirmation(Confirmation confirmation);
}