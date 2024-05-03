package org.mycorp.models.events.commev;

import org.mycorp.models.events.StationEvent;
import org.springframework.context.ApplicationEvent;

public class EVPowerDeliveryRequest extends StationEvent {
    public EVPowerDeliveryRequest(Object source) {
        super(source);
    }
}
