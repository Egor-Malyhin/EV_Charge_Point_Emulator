package org.mycorp.stateoperator.eventlisteners;

import org.mycorp.models.StationStateAction;
import org.mycorp.models.events.commev.DisconnectedEV;
import org.mycorp.stateoperator.StateOperatorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DisconnectedEVEventListener extends StateOperatorEventListener<DisconnectedEV> {
    @Autowired
    protected DisconnectedEVEventListener(StateOperatorInterface stateOperatorInterface) {
        super(stateOperatorInterface);
    }

    @Override
    @EventListener
    public void listenEvent(DisconnectedEV stationEvent) {
        stateOperatorInterface.setStationState(StationStateAction.GET_AVAILABLE);
    }
}
