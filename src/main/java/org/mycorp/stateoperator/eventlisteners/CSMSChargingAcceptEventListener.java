package org.mycorp.stateoperator.eventlisteners;

import org.mycorp.models.StationStateAction;
import org.mycorp.models.events.commcsms.CSMSChargingAccept;
import org.mycorp.stateoperator.StateOperatorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CSMSChargingAcceptEventListener extends StateOperatorEventListener<CSMSChargingAccept> {
    @Autowired
    protected CSMSChargingAcceptEventListener(StateOperatorInterface stateOperatorInterface) {
        super(stateOperatorInterface);
    }

    @Override
    @EventListener
    public void listenEvent(CSMSChargingAccept stationEvent) {
        stateOperatorInterface.setStationState(StationStateAction.START_CHARGING);
    }
}
