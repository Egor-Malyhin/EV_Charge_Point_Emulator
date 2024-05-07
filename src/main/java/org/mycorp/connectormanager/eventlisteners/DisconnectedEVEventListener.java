package org.mycorp.connectormanager.eventlisteners;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import org.mycorp.connectormanager.ConnectorManagerInterface;
import org.mycorp.models.StationVariables;
import org.mycorp.models.events.commev.DisconnectedEV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("disconnectedEVEventListenerConnectorManager")
public class DisconnectedEVEventListener extends ConnectorManagerEventListener<DisconnectedEV> {
    @Autowired
    protected DisconnectedEVEventListener(ConnectorManagerInterface connectorManagerInterface) {
        super(connectorManagerInterface);
    }

    @Override
    @EventListener
    public void listenEvent(DisconnectedEV stationEvent) {
        if (!stationEvent.isDisconnectionAccept() && StationVariables.getInstance().getChargePointStatusPast() == ChargePointStatus.Charging)
            connectorManagerInterface.requestStopChargingEmergency();
        else
            connectorManagerInterface.unlockConnector();
    }
}
