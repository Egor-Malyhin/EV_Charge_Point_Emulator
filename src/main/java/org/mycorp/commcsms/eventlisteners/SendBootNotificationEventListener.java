package org.mycorp.commcsms.eventlisteners;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.model.Request;
import org.mycorp.commcsms.CSMSCommunicationBlockInterface;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.events.commcsmshelper.SendBootNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SendBootNotificationEventListener extends CSMSCommunicationBlockEventListener<SendBootNotification> {
    @Autowired
    protected SendBootNotificationEventListener(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ClientCoreProfile clientCoreProfileMessageGenerator) {
        super(csmsCommunicationBlockInterface, clientCoreProfileMessageGenerator);
    }

    @Override
    @EventListener
    public void listenEvent(SendBootNotification stationEvent) {
        Request bootNotificationRequest = clientCoreProfileMessageGenerator.createBootNotificationRequest(StationCharacteristics.chargePointVendor, StationCharacteristics.chargePointModel);
        csmsCommunicationBlockInterface.addToMessageQueue(bootNotificationRequest);
    }
}
