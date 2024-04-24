package org.mycorp.commcsms;

import org.mycorp.models.messages.ocpp.OCPPMessage;

public interface CSMSCommunicationBlockInterface {
    void addToMessageQueue(OCPPMessage ocppMessage);
}
