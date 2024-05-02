package org.mycorp.stateoperator.eventlisteners;

import org.mycorp.models.StationStateAction;
import org.mycorp.models.events.chargetransfer.ChargingStopped;
import org.mycorp.stateoperator.StateOperatorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("stateOperatorChargingStoppedEventListener")
public class ChargingStoppedEventListener extends StateOperatorEventListener<ChargingStopped> {
    @Autowired
    protected ChargingStoppedEventListener(StateOperatorInterface stateOperatorInterface) {
        super(stateOperatorInterface);
    }

    @Override
    @EventListener
    public void listenEvent(ChargingStopped stationEvent) {
        stateOperatorInterface.setStationState(StationStateAction.FINISH_CHARGING);
    }
}
