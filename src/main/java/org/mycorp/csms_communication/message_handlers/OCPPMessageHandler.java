package org.mycorp.csms_communication.message_handlers;

import org.mycorp.models.ocpp_messages.OCPPMessage;

public interface OCPPMessageHandler {
    void handleMessage(OCPPMessage message);
}