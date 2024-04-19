package org.mycorp.models.station_events.csms_comm_events;

import org.springframework.context.ApplicationEvent;

public class EVAuthorized extends ApplicationEvent {
    private final boolean isAuthorized;

    public EVAuthorized(Object source, boolean isAuthorized) {
        super(source);
        this.isAuthorized = isAuthorized;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }
}