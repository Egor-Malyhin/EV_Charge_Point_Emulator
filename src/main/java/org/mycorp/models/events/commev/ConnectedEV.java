package org.mycorp.models.events.commev;

import org.springframework.context.ApplicationEvent;

public class ConnectedEV extends ApplicationEvent {
    public ConnectedEV(Object source) {
        super(source);
    }
}
