package org.mycorp.chargetransfer.eventpublishers.stopcharging;

import org.mycorp.models.events.chargetransfer.ChargingStoppedByEV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StopChargingByEVPublisher extends StopChargingEventPublisherImpl{
    @Autowired
    protected StopChargingByEVPublisher(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void publishStopChargingEvent() {
        applicationEventPublisher.publishEvent(new ChargingStoppedByEV(this));
    }
}
