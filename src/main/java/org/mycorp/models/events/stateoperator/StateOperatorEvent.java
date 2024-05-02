package org.mycorp.models.events.stateoperator;

import org.mycorp.stateoperator.StateOperator;
import org.springframework.context.ApplicationEvent;

public abstract class StateOperatorEvent extends ApplicationEvent {
    public StateOperatorEvent() {
        super(StateOperator.class);
    }
}
