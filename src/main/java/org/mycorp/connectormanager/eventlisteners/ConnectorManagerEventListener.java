package org.mycorp.connectormanager.eventlisteners;

import org.mycorp.connectormanager.ConnectorManagerInterface;
import org.mycorp.models.events.StationEvent;
import org.mycorp.stationeventlistener.StationEventListener;
import org.springframework.context.ApplicationEventPublisher;

public abstract class ConnectorManagerEventListener<T extends StationEvent> implements StationEventListener<T> {
    protected final ConnectorManagerInterface connectorManagerInterface;

    protected ConnectorManagerEventListener(ConnectorManagerInterface connectorManagerInterface) {
        this.connectorManagerInterface = connectorManagerInterface;
    }
}
