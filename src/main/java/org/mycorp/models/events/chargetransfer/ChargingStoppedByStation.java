package org.mycorp.models.events.chargetransfer;

import org.springframework.context.ApplicationEvent;

public class ChargingStoppedByStation extends ApplicationEvent {
    public ChargingStoppedByStation(Object source) {
        super(source);
    }
}
