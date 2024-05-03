package org.mycorp.models.events.commcsms;

public class CSMSChargingAccept extends CSMSCommunicationBlockEvent {
    private final boolean isAccepted;

    public CSMSChargingAccept(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }


    public boolean isAccepted() {
        return isAccepted;
    }
}
