package org.mycorp.stateoperator.statemachinelistener;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import org.mycorp.models.StationStateAction;
import org.mycorp.models.events.stateoperator.StateChanged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

@Component
public class StationStateMachineListener extends StateMachineListenerAdapter<ChargePointStatus, StationStateAction> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public StationStateMachineListener(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void stateChanged(State<ChargePointStatus, StationStateAction> from, State<ChargePointStatus, StationStateAction> to) {
        applicationEventPublisher.publishEvent(new StateChanged(to.getId()));
    }
}
