package org.mycorp.commcsms.eventlisteners;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.model.Request;
import org.mycorp.commcsms.CSMSCommunicationBlockInterface;
import org.mycorp.models.events.commev.EVStartSessionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EVStartSessionRequestEventListener extends CSMSCommunicationBlockEventListener<EVStartSessionRequest> {
    @Autowired
    protected EVStartSessionRequestEventListener(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ClientCoreProfile clientCoreProfileMessageGenerator) {
        super(csmsCommunicationBlockInterface, clientCoreProfileMessageGenerator);
    }

    @Override
    @EventListener
    public void listenEvent(EVStartSessionRequest stationEvent) {
        Request authorizeRequest = clientCoreProfileMessageGenerator.createAuthorizeRequest(stationEvent.getIdTag());
        csmsCommunicationBlockInterface.addToMessageQueue(authorizeRequest);
    }
}
