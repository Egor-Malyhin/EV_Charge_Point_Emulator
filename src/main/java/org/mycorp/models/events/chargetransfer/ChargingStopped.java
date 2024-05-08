package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.events.StationEvent;

public abstract class ChargingStopped extends StationEvent {
    public ChargingStopped(Object source) {
        super(source);
    }
}
