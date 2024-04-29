package org.mycorp.models.events.commev;

import org.mycorp.models.messages.v2g.types.ChargeProgress;
import org.springframework.context.ApplicationEvent;

public class EVPowerDeliveryRequest extends ApplicationEvent {
    public EVPowerDeliveryRequest(Object source) {
        super(source);
    }
}
