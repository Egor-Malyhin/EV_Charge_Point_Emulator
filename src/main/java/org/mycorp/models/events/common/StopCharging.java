package org.mycorp.models.events.common;

import org.springframework.context.ApplicationEvent;

public class StopCharging extends ApplicationEvent {
    private final String shutdownInitiator;

    public StopCharging(Object source, String shutdownInitiator) {
        super(source);
        this.shutdownInitiator = shutdownInitiator;
    }

    public String getShutdownInitiator() {
        return shutdownInitiator;
    }
}
