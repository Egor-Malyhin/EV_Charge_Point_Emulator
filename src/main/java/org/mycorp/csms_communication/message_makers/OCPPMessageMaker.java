package org.mycorp.csms_communication.message_makers;

import org.mycorp.csms_communication.CSMSCommunicationBlockInterface;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class OCPPMessageMaker {
    protected final CSMSCommunicationBlockInterface csmsCommunicationBlockInterface;

    @Autowired
    protected OCPPMessageMaker(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface) {
        this.csmsCommunicationBlockInterface = csmsCommunicationBlockInterface;
    }
}
