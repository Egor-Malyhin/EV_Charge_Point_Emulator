package org.mycorp.chargetransfer.eventpublishers.stopcharging;

import org.mycorp.models.events.chargetransfer.ChargingStoppedEmergency;
import org.mycorp.models.events.chargetransfer.ChargingStoppedNormally;
import org.mycorp.stationeventpublisher.StationEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StopChargingEventPublisher extends StationEventPublisher {
    protected StopChargingEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    public void publishStopChargingEvent(boolean isEmergencyStopping) {
        if (isEmergencyStopping)
            applicationEventPublisher.publishEvent(new ChargingStoppedEmergency(this));
        else
            applicationEventPublisher.publishEvent(new ChargingStoppedNormally(this));
    }
}
