package org.mycorp.commcsms.message_handlers;

import org.mycorp.models.messages.ocpp.OCPPMessage;

public interface OCPPMessageHandler {
    void handleMessage(OCPPMessage message);
}