package org.mycorp.models.events.commcsms;

import org.springframework.context.ApplicationEvent;

public class ClientConnected extends ApplicationEvent {
    public ClientConnected(Object source) {
        super(source);
    }
}
