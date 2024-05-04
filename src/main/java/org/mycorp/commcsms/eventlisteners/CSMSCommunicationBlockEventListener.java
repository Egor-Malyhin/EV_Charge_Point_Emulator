package org.mycorp.commcsms.eventlisteners;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import org.mycorp.commcsms.CSMSCommunicationBlockInterface;
import org.mycorp.models.events.StationEvent;
import org.mycorp.stationeventlistener.StationEventListener;

//extend this class for listen event from other blocks
//and generating based on their OCPP requests
public abstract class CSMSCommunicationBlockEventListener<T extends StationEvent> implements StationEventListener<T> {
    protected final CSMSCommunicationBlockInterface csmsCommunicationBlockInterface;
    protected final ClientCoreProfile clientCoreProfileMessageGenerator;

    protected CSMSCommunicationBlockEventListener(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ClientCoreProfile clientCoreProfileMessageGenerator) {
        this.csmsCommunicationBlockInterface = csmsCommunicationBlockInterface;
        this.clientCoreProfileMessageGenerator = clientCoreProfileMessageGenerator;
    }
}
