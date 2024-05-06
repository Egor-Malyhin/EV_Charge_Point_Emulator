package org.mycorp.commcsms.eventlisteners;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import org.mycorp.commcsms.CSMSCommunicationBlockInterface;
import org.mycorp.models.events.commcsmshelper.TryConnecting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TryConnectingEventListener extends CSMSCommunicationBlockEventListener<TryConnecting> {
    @Autowired
    protected TryConnectingEventListener(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ClientCoreProfile clientCoreProfileMessageGenerator) {
        super(csmsCommunicationBlockInterface, clientCoreProfileMessageGenerator);
    }

    @Override
    @EventListener
    public void listenEvent(TryConnecting stationEvent) {
        csmsCommunicationBlockInterface.connectToCSMS();
    }
}
