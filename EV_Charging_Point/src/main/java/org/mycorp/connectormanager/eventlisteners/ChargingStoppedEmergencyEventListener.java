package org.mycorp.connectormanager.eventlisteners;

import org.mycorp.connectormanager.ConnectorManagerInterface;
import org.mycorp.models.events.chargetransfer.ChargingStoppedEmergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ChargingStoppedEmergencyEventListener extends ConnectorManagerEventListener<ChargingStoppedEmergency> {
    @Autowired
    protected ChargingStoppedEmergencyEventListener(ConnectorManagerInterface connectorManagerInterface) {
        super(connectorManagerInterface);
    }

    @Override
    @EventListener
    public void listenEvent(ChargingStoppedEmergency stationEvent) {
        connectorManagerInterface.unlockConnector();
    }
}
