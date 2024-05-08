package org.mycorp.commcsmshelper.eventlisteners;

import org.mycorp.commcsmshelper.CSMSCommunicationBlockHelperInterface;
import org.mycorp.models.events.commcsms.CSMSDisconnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CSMSDisconnectEventListener extends CSMSCommunicationBlockHelperEventListener<CSMSDisconnect> {
    @Autowired
    protected CSMSDisconnectEventListener(CSMSCommunicationBlockHelperInterface csmsCommunicationBlockHelperInterface) {
        super(csmsCommunicationBlockHelperInterface);
    }

    @Override
    @EventListener
    public void listenEvent(CSMSDisconnect stationEvent) {
        csmsCommunicationBlockHelperInterface.tryConnecting();
    }
}
