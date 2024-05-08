package org.mycorp.commcsms.eventlisteners;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.model.Request;
import org.mycorp.commcsms.CSMSCommunicationBlockInterface;
import org.mycorp.models.StationVariables;
import org.mycorp.models.events.chargetransfer.ChargingStopped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component("csmsChargingStoppedEventListener")
public class ChargingStoppedEventListener extends CSMSCommunicationBlockEventListener<ChargingStopped> {
    @Autowired
    protected ChargingStoppedEventListener(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ClientCoreProfile clientCoreProfileMessageGenerator) {
        super(csmsCommunicationBlockInterface, clientCoreProfileMessageGenerator);
    }


    @Override
    @EventListener
    public void listenEvent(ChargingStopped stationEvent) {
        StationVariables stationVariables = StationVariables.getInstance();
        Request stopTransactionRequest = clientCoreProfileMessageGenerator.createStopTransactionRequest(stationVariables.getMeterCurrent(), Instant.now().atZone(stationVariables.getZoneId()), stationVariables.getTransactionId());
        csmsCommunicationBlockInterface.addToMessageQueue(stopTransactionRequest);
    }
}
