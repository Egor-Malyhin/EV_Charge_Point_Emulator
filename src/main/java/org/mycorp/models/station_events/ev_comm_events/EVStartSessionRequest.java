package org.mycorp.models.station_events.ev_comm_events;

import org.springframework.context.ApplicationEvent;

public class EVStartSessionRequest extends ApplicationEvent {
    private final String idTag;

    public EVStartSessionRequest(Object source, String idTag) {
        super(source);
        this.idTag = idTag;
    }

    public String getIdTag() {
        return idTag;
    }
}
