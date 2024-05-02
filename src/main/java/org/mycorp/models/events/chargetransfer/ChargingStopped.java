package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.events.common.StopCharging;

public class ChargingStopped extends ChargeTransferEvent {
    private final String shutdownInitiator;

    public ChargingStopped(String shutdownInitiator) {
        this.shutdownInitiator = shutdownInitiator;
    }

    public String getShutdownInitiator() {
        return shutdownInitiator;
    }
}
