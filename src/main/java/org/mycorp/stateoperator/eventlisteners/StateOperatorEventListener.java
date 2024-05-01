package org.mycorp.stateoperator.eventlisteners;

import org.mycorp.stateoperator.StateOperatorInterface;
import org.mycorp.stationeventlistener.StationEventListener;
import org.springframework.context.ApplicationEvent;

public abstract class StateOperatorEventListener<T extends ApplicationEvent> implements StationEventListener<T> {
    protected final StateOperatorInterface stateOperatorInterface;

    protected StateOperatorEventListener(StateOperatorInterface stateOperatorInterface) {
        this.stateOperatorInterface = stateOperatorInterface;
    }
}
