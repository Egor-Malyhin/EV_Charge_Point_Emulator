package org.mycorp.models.events.commev;

import org.mycorp.models.events.StationEvent;
import org.springframework.context.ApplicationEvent;

public class EVSessionStopRequest extends StationEvent {
    public EVSessionStopRequest(Object source) {
        super(source);
    }
}
