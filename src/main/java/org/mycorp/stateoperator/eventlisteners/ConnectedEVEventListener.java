package org.mycorp.stateoperator.eventlisteners;

import org.mycorp.models.StationStateAction;
import org.mycorp.models.events.commev.ConnectedEV;
import org.mycorp.stateoperator.StateOperatorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class ConnectedEVEventListener extends StateOperatorEventListener<ConnectedEV> {
    @Autowired
    protected ConnectedEVEventListener(StateOperatorInterface stateOperatorInterface) {
        super(stateOperatorInterface);
    }

    @Override
    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void listenEvent(ConnectedEV stationEvent) {
        stateOperatorInterface.setStationState(StationStateAction.PREPARE_CHARGING);
    }
}
