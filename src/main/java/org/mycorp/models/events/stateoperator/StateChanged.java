package org.mycorp.models.events.stateoperator;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import org.springframework.context.ApplicationEvent;

public class StateChanged extends StateOperatorEvent {
    private final ChargePointStatus chargePointStatus;

    public StateChanged(ChargePointStatus chargePointStatus) {
        this.chargePointStatus = chargePointStatus;
    }

    public ChargePointStatus getChargePointStatus() {
        return chargePointStatus;
    }
}
