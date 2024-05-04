package org.mycorp.models.events.commcsms;

import org.mycorp.models.events.StationEvent;

public class ClientConnected extends StationEvent {
    public ClientConnected(Object source) {
        super(source);
    }
}
