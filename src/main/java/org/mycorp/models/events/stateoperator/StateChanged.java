package org.mycorp.models.events.stateoperator;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import org.springframework.context.ApplicationEvent;

public class StateChanged extends ApplicationEvent {
    private final ChargePointStatus chargePointStatus;
    public StateChanged(Object source, ChargePointStatus chargePointStatus) {
        super(source);
        this.chargePointStatus = chargePointStatus;
    }

    public ChargePointStatus getChargePointStatus() {
        return chargePointStatus;
    }
}
