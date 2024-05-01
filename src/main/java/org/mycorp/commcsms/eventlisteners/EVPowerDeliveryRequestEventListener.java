package org.mycorp.commcsms.eventlisteners;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.model.Request;
import org.mycorp.commcsms.CSMSCommunicationBlockInterface;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.StationVariables;
import org.mycorp.models.events.commev.EVPowerDeliveryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class EVPowerDeliveryRequestEventListener extends CSMSCommunicationBlockEventListener<EVPowerDeliveryRequest> {
    @Autowired
    protected EVPowerDeliveryRequestEventListener(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ClientCoreProfile clientCoreProfileMessageGenerator) {
        super(csmsCommunicationBlockInterface, clientCoreProfileMessageGenerator);
    }

    @Override
    @EventListener
    public void listenEvent(EVPowerDeliveryRequest stationEvent) {
        StationVariables stationVariables = StationVariables.getInstance();
        Request startTransactionRequest = clientCoreProfileMessageGenerator.createStartTransactionRequest(StationCharacteristics.connectorId, stationVariables.getIdTag(), stationVariables.getMeterCurrent(), Instant.now().atZone(stationVariables.getZoneId()));
        csmsCommunicationBlockInterface.addToMessageQueue(startTransactionRequest);
    }
}
