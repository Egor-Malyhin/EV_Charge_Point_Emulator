package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.events.StationEvent;

public class ChargingStoppedEmergency extends ChargingStopped {
    public ChargingStoppedEmergency(Object source) {
        super(source);
    }
}
