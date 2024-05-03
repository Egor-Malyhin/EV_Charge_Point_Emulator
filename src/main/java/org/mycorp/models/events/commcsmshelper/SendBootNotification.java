package org.mycorp.models.events.commcsmshelper;

import org.mycorp.models.events.StationEvent;
import org.springframework.context.ApplicationEvent;

public class SendBootNotification extends StationEvent {
    public SendBootNotification(Object source) {
        super(source);
    }
}
