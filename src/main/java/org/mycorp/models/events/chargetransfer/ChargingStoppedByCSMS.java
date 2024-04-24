package org.mycorp.models.events.chargetransfer;

import org.springframework.context.ApplicationEvent;

public class ChargingStoppedByCSMS extends ApplicationEvent {
    public ChargingStoppedByCSMS(Object source) {
        super(source);
    }
}
