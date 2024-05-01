package org.mycorp.models.events.commev;

import org.springframework.context.ApplicationEvent;

public class EVPowerDeliveryRequest extends ApplicationEvent {
    public EVPowerDeliveryRequest(Object source) {
        super(source);
    }
}
