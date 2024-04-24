package org.mycorp.models.events.chargetransfer;

import org.springframework.context.ApplicationEvent;

public class ChargingStoppedByEV extends ApplicationEvent {
    public ChargingStoppedByEV(Object source) {
        super(source);
    }
}
