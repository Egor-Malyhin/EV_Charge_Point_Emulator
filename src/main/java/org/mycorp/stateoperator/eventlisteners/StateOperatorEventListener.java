package org.mycorp.stateoperator.eventlisteners;

import org.mycorp.models.events.StationEvent;
import org.mycorp.stateoperator.StateOperatorInterface;
import org.mycorp.stationeventlistener.StationEventListener;

public abstract class StateOperatorEventListener<T extends StationEvent> implements StationEventListener<T> {
    protected final StateOperatorInterface stateOperatorInterface;

    protected StateOperatorEventListener(StateOperatorInterface stateOperatorInterface) {
        this.stateOperatorInterface = stateOperatorInterface;
    }
}
