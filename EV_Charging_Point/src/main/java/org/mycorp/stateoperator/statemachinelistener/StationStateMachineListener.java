package org.mycorp.stateoperator.statemachinelistener;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import lombok.extern.slf4j.Slf4j;
import org.mycorp.models.StationStateAction;
import org.mycorp.models.StationVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StationStateMachineListener extends StateMachineListenerAdapter<ChargePointStatus, StationStateAction> {
    private final StateChangedPublisher stateChangedPublisher;

    @Autowired
    public StationStateMachineListener(StateChangedPublisher stateChangedPublisher) {
        this.stateChangedPublisher = stateChangedPublisher;
    }

    @Override
    public void eventNotAccepted(Message<StationStateAction> event) {
        throw new IllegalStateException("Invalid transition: " + event.getPayload());
    }

    @Override
    public void stateChanged(State<ChargePointStatus, StationStateAction> from, State<ChargePointStatus, StationStateAction> to) {
        ChargePointStatus pastStatus = (from != null) ? from.getId() : null;
        StationVariables stationVariables = StationVariables.getInstance();
        stationVariables.setChargePointStatus(to.getId(), pastStatus);
        log.info("New Session State: " + to.getId() + ", Past Session State: " + pastStatus);
        stateChangedPublisher.publishStateChangedEvent(to.getId());
    }
}
