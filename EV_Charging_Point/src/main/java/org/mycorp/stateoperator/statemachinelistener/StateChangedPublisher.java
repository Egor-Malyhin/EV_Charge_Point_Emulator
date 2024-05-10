package org.mycorp.stateoperator.statemachinelistener;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import org.mycorp.models.events.stateoperator.StateChanged;
import org.mycorp.stationeventpublisher.StationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

//Moved the event sending logic to a separate class
//to avoid breaking the project architecture
//(classes sending events should inherit from StationEventPublisher),
//as the StationStateMachineListener class already inherits from StateMachineListenerAdapter.
@Component
public class StateChangedPublisher extends StationEventPublisher {
    public void stateChanged(ChargePointStatus chargePointStatus) {
        applicationEventPublisher.publishEvent(new StateChanged(this, chargePointStatus));
    }
}
