package org.mycorp.models.events.common;

import org.springframework.context.ApplicationEvent;

public class StopCharging extends ApplicationEvent {
    public StopCharging(Object source) {
        super(source);
    }
}
