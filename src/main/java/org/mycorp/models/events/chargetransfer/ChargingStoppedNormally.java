package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.events.StationEvent;

public class ChargingStoppedNormally extends ChargingStopped {
    public ChargingStoppedNormally(Object source) {
        super(source);
    }
}
