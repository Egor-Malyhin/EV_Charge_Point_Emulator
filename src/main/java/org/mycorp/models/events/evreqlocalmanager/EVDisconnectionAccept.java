package org.mycorp.models.events.evreqlocalmanager;

import org.springframework.context.ApplicationEvent;

public class EVDisconnectionAccept extends ApplicationEvent {
    public EVDisconnectionAccept(Object source) {
        super(source);
    }
}
