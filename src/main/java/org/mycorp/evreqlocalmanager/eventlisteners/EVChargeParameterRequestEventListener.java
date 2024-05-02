package org.mycorp.evreqlocalmanager.eventlisteners;

import org.mycorp.evreqlocalmanager.EVLocalRequestManager;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.events.commev.EVChargeParameterRequest;
import org.mycorp.models.events.evreqlocalmanager.StationParametersPresented;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EVChargeParameterRequestEventListener extends EVLocalRequestManager<EVChargeParameterRequest> {
    protected EVChargeParameterRequestEventListener(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    @EventListener
    public void listenEvent(EVChargeParameterRequest stationEvent) {
        applicationEventPublisher.publishEvent(new StationParametersPresented(StationCharacteristics.ratedVoltage, StationCharacteristics.maxCurrent));
    }
}
