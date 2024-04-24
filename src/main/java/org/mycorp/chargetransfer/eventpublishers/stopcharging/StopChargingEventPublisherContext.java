package org.mycorp.chargetransfer.eventpublishers.stopcharging;

import org.mycorp.commev.messagehandlers.V2GMessageHandler;
import org.mycorp.models.messages.v2g.V2GBodyAbstractType;
import org.mycorp.models.messages.v2g.V2GMessagesClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StopChargingEventPublisherContext {
    private final Map<String, StopChargingEventPublisher> stopChargingEventPublisherMap;

    @Autowired
    public StopChargingEventPublisherContext(Map<String, StopChargingEventPublisher> stopChargingEventPublisherMap) {
        this.stopChargingEventPublisherMap = stopChargingEventPublisherMap;
    }

    public StopChargingEventPublisher getStopChargingEventPublisherImpl(String shutdownInitiator) {
        return stopChargingEventPublisherMap.get(shutdownInitiator);
    }
}
