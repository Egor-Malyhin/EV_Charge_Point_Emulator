package org.mycorp.models.station_events.csms_comm_events;

import org.springframework.context.ApplicationEvent;

public class CSMSChargingAccept extends ApplicationEvent {
    private final boolean isAccepted;

    public CSMSChargingAccept(Object source, boolean isAccepted) {
        super(source);
        this.isAccepted = isAccepted;
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}
