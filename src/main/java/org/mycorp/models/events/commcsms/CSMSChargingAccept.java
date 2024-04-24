package org.mycorp.models.events.commcsms;

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
