package org.mycorp.main;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import org.mycorp.models.StationStateAction;
import org.mycorp.stateoperator.statemachinelistener.StationStateMachineListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;


//The configuration class of the state machine.
//The enum constants of the OCA-OCPP library are used as states,
//and the enum constants of StationStateAction are used as transitions between states.
//A statemachine listener is also used.
@Configuration
@EnableStateMachine
public class StationStateMachineConfiguration extends EnumStateMachineConfigurerAdapter<ChargePointStatus, StationStateAction> {
    private final StationStateMachineListener stationStateMachineListener;

    @Autowired
    public StationStateMachineConfiguration(StationStateMachineListener stationStateMachineListener) {
        this.stationStateMachineListener = stationStateMachineListener;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<ChargePointStatus, StationStateAction> config)
            throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(stationStateMachineListener);
    }

    @Override
    public void configure(StateMachineStateConfigurer<ChargePointStatus, StationStateAction> states) throws Exception {
        states
                .withStates()
                .initial(ChargePointStatus.Available)
                .state(ChargePointStatus.Preparing)
                .state(ChargePointStatus.Charging)
                .state(ChargePointStatus.Finishing);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ChargePointStatus, StationStateAction> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(ChargePointStatus.Available).target(ChargePointStatus.Preparing)
                .event(StationStateAction.PREPARE_CHARGING)
                .and()
                .withExternal()
                .source(ChargePointStatus.Preparing).target(ChargePointStatus.Charging)
                .event(StationStateAction.START_CHARGING)
                .and()
                .withExternal()
                .source(ChargePointStatus.Charging).target(ChargePointStatus.Finishing)
                .event(StationStateAction.FINISH_CHARGING)
                .and()
                .withExternal()
                .source(ChargePointStatus.Finishing).target(ChargePointStatus.Available)
                .event(StationStateAction.GET_AVAILABLE);
    }
}
