package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.models.events.connectormanager.UnlockConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("unlockConnectorEventListenerCommEV")
public class UnlockConnectorEventListener extends EVCommunicationBlockEventListener<UnlockConnector> {
    @Autowired
    protected UnlockConnectorEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        super(evCommunicationBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(UnlockConnector stationEvent) {
        evCommunicationBlockInterface.availableConnection();
    }
}
