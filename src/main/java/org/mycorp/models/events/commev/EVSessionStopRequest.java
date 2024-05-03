package org.mycorp.models.events.commev;

import org.springframework.context.ApplicationEvent;

public class EVSessionStopRequest extends ApplicationEvent {
    public EVSessionStopRequest(Object source) {
        super(source);
    }
}
