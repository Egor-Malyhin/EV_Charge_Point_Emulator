package org.mycorp.csms_communication;

import org.mycorp.models.ocpp_messages.OCPPMessage;

public interface CSMSCommunicationBlockInterface {
    void addToMessageQueue(OCPPMessage ocppMessage);
}
