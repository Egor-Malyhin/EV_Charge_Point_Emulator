package org.mycorp.models.events.common;

import org.springframework.context.ApplicationEvent;

public class GetMeterValues extends ApplicationEvent {
    public GetMeterValues(Object source) {
        super(source);
    }
}
