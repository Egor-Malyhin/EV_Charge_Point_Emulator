package org.mycorp.models.events.commcsms;

import org.mycorp.models.events.StationEvent;

public class EVAuthorized extends StationEvent {
    private final boolean isAuthorized;

    public EVAuthorized(Object source, boolean isAuthorized) {
        super(source);
        this.isAuthorized = isAuthorized;
    }


    public boolean isAuthorized() {
        return isAuthorized;
    }
}