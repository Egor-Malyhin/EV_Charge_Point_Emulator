package org.mycorp.models.events.commev;

import org.springframework.context.ApplicationEvent;

public class DisconnectedEV extends ApplicationEvent {
    public DisconnectedEV(Object source) {
        super(source);
    }
}
