package org.mycorp.models.station_events.ev_comm_events;

import org.springframework.context.ApplicationEvent;

public class EVSessionStopRequest extends ApplicationEvent {
    public EVSessionStopRequest(Object source) {
        super(source);
    }
}
