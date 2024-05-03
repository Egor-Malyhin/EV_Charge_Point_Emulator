package org.mycorp.models.events.commev;

import org.mycorp.models.events.StationEvent;
import org.springframework.context.ApplicationEvent;

public class ConnectedEV extends StationEvent {
    public ConnectedEV(Object source) {
        super(source);
    }
}
