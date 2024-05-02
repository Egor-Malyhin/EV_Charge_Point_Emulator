package org.mycorp.models.events.commcsms;

import org.springframework.context.ApplicationEvent;

public class CSMSChargingAccept extends CSMSCommunicationBlockEvent {
    private final boolean isAccepted;

    public CSMSChargingAccept(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }


    public boolean isAccepted() {
        return isAccepted;
    }
}
