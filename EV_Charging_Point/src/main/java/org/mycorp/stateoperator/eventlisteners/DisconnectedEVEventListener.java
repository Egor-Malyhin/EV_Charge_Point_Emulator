package org.mycorp.stateoperator.eventlisteners;

import org.mycorp.models.StationStateAction;
import org.mycorp.models.events.commev.DisconnectedEV;
import org.mycorp.stateoperator.StateOperatorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//By default, it locks the connector immediately after the electric vehicle is disconnected.
//To change the state to Available, it waits for the UnlockConnector event from the ConnectorManager.
@Component("disconnectedEVEventListenerStateOperator")
public class DisconnectedEVEventListener extends StateOperatorEventListener<DisconnectedEV> {
    @Autowired
    protected DisconnectedEVEventListener(StateOperatorInterface stateOperatorInterface) {
        super(stateOperatorInterface);
    }

    @Override
    @EventListener
    @Order(1)
    public void listenEvent(DisconnectedEV stationEvent) {
        stateOperatorInterface.setStationState(StationStateAction.GET_UNAVAILABLE);
    }
}
