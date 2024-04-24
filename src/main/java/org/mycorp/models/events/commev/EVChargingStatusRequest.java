package org.mycorp.models.events.commev;

import org.springframework.context.ApplicationEvent;

public class EVChargingStatusRequest extends ApplicationEvent {
    public EVChargingStatusRequest(Object source) {
        super(source);
    }
}
