package org.mycorp.models.events.common;

import org.springframework.context.ApplicationEvent;

public class GetMeterValues extends ApplicationEvent {
    private final String requester;

    public GetMeterValues(Object source, String requester) {
        super(source);
        this.requester = requester;
    }

    public String getRequester() {
        return requester;
    }
}
