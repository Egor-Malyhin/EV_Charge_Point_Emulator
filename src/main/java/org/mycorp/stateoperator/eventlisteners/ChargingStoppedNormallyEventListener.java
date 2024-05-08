package org.mycorp.stateoperator.eventlisteners;

import org.mycorp.models.StationStateAction;
import org.mycorp.models.events.chargetransfer.ChargingStoppedNormally;
import org.mycorp.stateoperator.StateOperatorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component("stateOperatorChargingStoppedEventListener")
public class ChargingStoppedNormallyEventListener extends StateOperatorEventListener<ChargingStoppedNormally> {
    @Autowired
    protected ChargingStoppedNormallyEventListener(StateOperatorInterface stateOperatorInterface) {
        super(stateOperatorInterface);
    }

    @Override
    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void listenEvent(ChargingStoppedNormally stationEvent) {
        stateOperatorInterface.setStationState(StationStateAction.FINISH_CHARGING);
    }
}
