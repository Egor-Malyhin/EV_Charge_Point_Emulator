package org.mycorp.commcsms;

import eu.chargetime.ocpp.model.Request;

public interface CSMSCommunicationBlockInterface {
    void addToMessageQueue(Request ocppRequest);
}
