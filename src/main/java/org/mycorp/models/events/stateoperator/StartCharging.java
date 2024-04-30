package org.mycorp.models.events.stateoperator;

import org.springframework.context.ApplicationEvent;

public class StartCharging extends ApplicationEvent {
    public StartCharging(Object source) {
        super(source);
    }
}
