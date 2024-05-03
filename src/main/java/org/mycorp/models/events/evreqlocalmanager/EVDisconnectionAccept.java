package org.mycorp.models.events.evreqlocalmanager;

import org.mycorp.models.events.StationEvent;
import org.springframework.context.ApplicationEvent;

public class EVDisconnectionAccept extends StationEvent {
    public EVDisconnectionAccept(Object source) {
        super(source);
    }
}
