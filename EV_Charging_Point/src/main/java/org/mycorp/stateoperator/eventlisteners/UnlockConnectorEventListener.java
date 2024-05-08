package org.mycorp.stateoperator.eventlisteners;

import org.mycorp.models.StationStateAction;
import org.mycorp.models.events.connectormanager.UnlockConnector;
import org.mycorp.stateoperator.StateOperatorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("unlockConnectorEventListenerStateOperator")
public class UnlockConnectorEventListener extends StateOperatorEventListener<UnlockConnector> {
    @Autowired
    protected UnlockConnectorEventListener(StateOperatorInterface stateOperatorInterface) {
        super(stateOperatorInterface);
    }

    @Override
    @EventListener
    public void listenEvent(UnlockConnector stationEvent) {
        stateOperatorInterface.setStationState(StationStateAction.GET_AVAILABLE);
    }
}
