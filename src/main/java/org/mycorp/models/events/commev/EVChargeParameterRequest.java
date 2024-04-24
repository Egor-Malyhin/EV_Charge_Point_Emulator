package org.mycorp.models.events.commev;

import org.mycorp.models.Charge;
import org.springframework.context.ApplicationEvent;

public class EVChargeParameterRequest extends ApplicationEvent {
    private final Charge amount;

    public EVChargeParameterRequest(Object source, Charge amount) {
        super(source);
        this.amount = amount;
    }

    public Charge getCharge(){
        return amount;
    }
}
