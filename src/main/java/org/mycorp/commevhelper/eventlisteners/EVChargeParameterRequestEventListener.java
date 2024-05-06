package org.mycorp.commevhelper.eventlisteners;

import org.mycorp.commevhelper.EVCommunicationBlockHelper;
import org.mycorp.commevhelper.EVCommunicationBlockHelperInterface;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.events.commev.EVChargeParameterRequest;
import org.mycorp.models.events.commevhelper.StationParametersPresented;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EVChargeParameterRequestEventListener extends EVCommunicationBlockHelperEventListener<EVChargeParameterRequest> {
    @Autowired
    protected EVChargeParameterRequestEventListener(EVCommunicationBlockHelperInterface evCommunicationBlockHelperInterface) {
        super(evCommunicationBlockHelperInterface);
    }

    @Override
    @EventListener
    public void listenEvent(EVChargeParameterRequest stationEvent) {
        evCommunicationBlockHelperInterface.presentChargeParameter();
    }
}
