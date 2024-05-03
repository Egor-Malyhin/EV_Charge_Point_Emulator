package org.mycorp.models.events.commev;

import lombok.Getter;
import org.mycorp.models.Charge;
import org.mycorp.models.events.StationEvent;
import org.springframework.context.ApplicationEvent;

@Getter
public class EVChargeParameterRequest extends StationEvent {
    private final Charge amount;

    public EVChargeParameterRequest(Object source, Charge amount) {
        super(source);
        this.amount = amount;
    }
}
