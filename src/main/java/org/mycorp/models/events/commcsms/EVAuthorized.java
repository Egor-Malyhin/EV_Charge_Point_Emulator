package org.mycorp.models.events.commcsms;

public class EVAuthorized extends CSMSCommunicationBlockEvent {
    private final boolean isAuthorized;

    public EVAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }
}