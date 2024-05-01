package org.mycorp.models.events;

import org.springframework.context.ApplicationEvent;

public class SendBootNotification extends ApplicationEvent {
    public SendBootNotification(Object source) {
        super(source);
    }
}
