package org.mycorp.models.events.chargetransfer;

public class ChargingStopped extends ChargeTransferEvent {
    private final String shutdownInitiator;

    public ChargingStopped(String shutdownInitiator) {
        this.shutdownInitiator = shutdownInitiator;
    }

    public String getShutdownInitiator() {
        return shutdownInitiator;
    }
}
