package org.mycorp.models.events.commev;

import org.mycorp.models.events.StationEvent;

public class DisconnectedEV extends StationEvent {
    public DisconnectedEV(Object source) {
        super(source);
    }
}
