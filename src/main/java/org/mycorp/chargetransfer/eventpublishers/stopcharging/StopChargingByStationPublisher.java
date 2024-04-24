package org.mycorp.chargetransfer.eventpublishers.stopcharging;

import org.mycorp.models.events.chargetransfer.ChargingStoppedByStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StopChargingByStationPublisher extends StopChargingEventPublisherImpl {
    @Autowired
    protected StopChargingByStationPublisher(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void publishStopChargingEvent() {
        applicationEventPublisher.publishEvent(new ChargingStoppedByStation(this));
    }
}
