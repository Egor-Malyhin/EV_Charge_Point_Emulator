package org.mycorp.models.events.commev;

import lombok.Getter;
import org.mycorp.models.events.StationEvent;

@Getter
public class DisconnectedEV extends StationEvent {
    private final boolean isDisconnectionAccept;

    public DisconnectedEV(Object source, boolean isDisconnectionAccept) {
        super(source);
        this.isDisconnectionAccept = isDisconnectionAccept;
    }
}
