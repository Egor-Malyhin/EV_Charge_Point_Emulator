package org.mycorp.models.events.commcsms;

import org.mycorp.models.events.StationEvent;
import org.springframework.context.ApplicationEvent;

public class ClientConnected extends StationEvent {
    public ClientConnected(Object source) {
        super(source);
    }
}
