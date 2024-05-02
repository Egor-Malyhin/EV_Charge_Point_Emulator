package org.mycorp.models.events.commev;

import org.mycorp.models.Charge;
import org.springframework.context.ApplicationEvent;

public class EVChargeParameterRequest extends EVCommunicationBlockEvent {
    private final Charge amount;

    public EVChargeParameterRequest(Charge amount) {
        this.amount = amount;
    }

    public Charge getCharge() {
        return amount;
    }
}
