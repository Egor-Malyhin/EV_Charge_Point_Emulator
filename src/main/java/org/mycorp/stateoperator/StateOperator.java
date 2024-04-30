package org.mycorp.stateoperator;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import org.mycorp.models.StationStateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;


//the main class of the stateoperator module. Contains the logic of changing the state of the station.
//When calling the method to change the state of the station, it sends an event to the state machine
//with an action by which the machine changes the state
@Component
public class StateOperator implements StateOperatorInterface {
    private final StateMachine<ChargePointStatus, StationStateAction> stateMachine;

    @Autowired
    public StateOperator(StateMachine<ChargePointStatus, StationStateAction> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void setStationState(StationStateAction stationStateAction) {
        stateMachine.sendEvent(stationStateAction);
    }
}
