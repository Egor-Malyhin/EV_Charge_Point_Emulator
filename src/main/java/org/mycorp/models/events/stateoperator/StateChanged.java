package org.mycorp.models.events.stateoperator;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StateChanged extends ApplicationEvent {
    private final ChargePointStatus chargePointStatus;

    public StateChanged(Object source, ChargePointStatus chargePointStatus) {
        super(source);
        this.chargePointStatus = chargePointStatus;
    }
}
