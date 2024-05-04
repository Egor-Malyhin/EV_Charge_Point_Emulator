package org.mycorp.models.events.chargetransfer;

import lombok.Getter;
import org.mycorp.models.events.StationEvent;

@Getter
public class ChargingStopped extends StationEvent {
    private final String shutdownInitiator;

    public ChargingStopped(Object source, String shutdownInitiator) {
        super(source);
        this.shutdownInitiator = shutdownInitiator;
    }

}
