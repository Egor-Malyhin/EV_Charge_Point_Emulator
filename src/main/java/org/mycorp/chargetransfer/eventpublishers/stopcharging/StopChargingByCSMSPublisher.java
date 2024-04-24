package org.mycorp.chargetransfer.eventpublishers.stopcharging;

import org.mycorp.models.events.chargetransfer.ChargingStoppedByCSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StopChargingByCSMSPublisher extends StopChargingEventPublisherImpl {
    @Autowired
    protected StopChargingByCSMSPublisher(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void publishStopChargingEvent() {
        applicationEventPublisher.publishEvent(new ChargingStoppedByCSMS(this));
    }
}
