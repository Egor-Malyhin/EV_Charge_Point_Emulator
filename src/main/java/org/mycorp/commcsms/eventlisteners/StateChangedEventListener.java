package org.mycorp.commcsms.eventlisteners;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.ChargePointErrorCode;
import org.mycorp.commcsms.CSMSCommunicationBlockInterface;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.events.stateoperator.StateChanged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StateChangedEventListener extends CSMSCommunicationBlockEventListener<StateChanged> {
    @Autowired
    protected StateChangedEventListener(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ClientCoreProfile clientCoreProfileMessageGenerator) {
        super(csmsCommunicationBlockInterface, clientCoreProfileMessageGenerator);
    }

    @Override
    @EventListener
    public void listenEvent(StateChanged stationEvent) {
        Request statusNotificationRequest = clientCoreProfileMessageGenerator.createStatusNotificationRequest(StationCharacteristics.connectorId, ChargePointErrorCode.NoError, stationEvent.getChargePointStatus());
        csmsCommunicationBlockInterface.addToMessageQueue(statusNotificationRequest);
    }
}
