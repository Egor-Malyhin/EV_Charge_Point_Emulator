package org.mycorp.chargetransfer.eventpublishers.stopcharging;

import org.mycorp.chargetransfer.eventpublishers.stopcharging.StopChargingEventPublisher;
import org.springframework.context.ApplicationEventPublisher;

public abstract class StopChargingEventPublisherImpl implements StopChargingEventPublisher {
    protected final ApplicationEventPublisher applicationEventPublisher;

    protected StopChargingEventPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
