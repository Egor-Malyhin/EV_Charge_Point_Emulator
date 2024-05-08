package org.mycorp.connectormanager;

import org.mycorp.models.events.common.StopChargingEmergency;
import org.mycorp.models.events.connectormanager.UnlockConnector;
import org.mycorp.stationeventpublisher.StationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

//
@Component
public class ConnectorManager extends StationEventPublisher implements ConnectorManagerInterface {
    @Autowired
    protected ConnectorManager(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void unlockConnector() {
        applicationEventPublisher.publishEvent(new UnlockConnector(this));
    }

    @Override
    public void requestStopChargingEmergency() {
        applicationEventPublisher.publishEvent(new StopChargingEmergency(this));
    }
}
