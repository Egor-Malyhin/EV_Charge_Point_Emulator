package org.mycorp.commcsmshelper.eventlisteners;

import org.mycorp.commcsmshelper.CSMSCommunicationBlockHelperInterface;
import org.mycorp.models.events.commcsms.ClientConnected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ClientConnectedEventListener extends CSMSCommunicationBlockHelperEventListener<ClientConnected> {
    @Autowired
    protected ClientConnectedEventListener(CSMSCommunicationBlockHelperInterface csmsCommunicationBlockHelperInterface) {
        super(csmsCommunicationBlockHelperInterface);
    }

    @Override
    @EventListener
    public void listenEvent(ClientConnected stationEvent) {
        csmsCommunicationBlockHelperInterface.sendBootNotification();
    }
}
