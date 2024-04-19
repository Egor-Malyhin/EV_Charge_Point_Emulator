package org.mycorp.models.station_events.ev_comm_events;

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
