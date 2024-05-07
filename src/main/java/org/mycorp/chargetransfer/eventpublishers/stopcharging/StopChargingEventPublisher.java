package org.mycorp.chargetransfer.eventpublishers.stopcharging;

import org.mycorp.models.events.chargetransfer.ChargingStoppedNormally;
import org.mycorp.models.events.chargetransfer.ChargingStoppedEmergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StopChargingEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public StopChargingEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishStopChargingEvent(boolean isEmergencyStopping) {
        if (isEmergencyStopping)
            applicationEventPublisher.publishEvent(new ChargingStoppedEmergency(this));
        else
            applicationEventPublisher.publishEvent(new ChargingStoppedNormally(this));
    }
}
