package org.mycorp.stateoperator;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import org.mycorp.models.StationStateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@Component
public class StationStateWrapper {
    private final StateMachine<ChargePointStatus, StationStateAction> stateMachine;
    private ChargePointStatus chargePointStatus;

    @Autowired
    private StationStateWrapper(StateMachine<ChargePointStatus, StationStateAction> stateMachine){
        this.stateMachine = stateMachine;
    }

    public ChargePointStatus getChargePointStatus(){
        updateChargePointStatus();
        return chargePointStatus;
    }

    private void updateChargePointStatus(){
        chargePointStatus = stateMachine.getState().getId();
    }
}
