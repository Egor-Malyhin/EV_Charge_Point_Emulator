package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.commev.messagefactory.V2GMessageResFactory;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.events.commcsms.EVAuthorized;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static org.mycorp.models.messages.v2g.types.enums.ResponseCode.FAILED;
import static org.mycorp.models.messages.v2g.types.enums.ResponseCode.OK_NewSessionEstablished;

@Component
public class EVAuthorizeEventListener extends EVCommunicationBlockEventListener<EVAuthorized> {
    @Autowired
    protected EVAuthorizeEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        super(evCommunicationBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(EVAuthorized stationEvent) {
        ResponseCode responseCode = stationEvent.isAuthorized() ? OK_NewSessionEstablished : FAILED;
        evCommunicationBlockInterface.sendMessage(V2GMessageResFactory.createSessionSetupResMessage(responseCode, StationCharacteristics.evseId, Instant.now().toEpochMilli()));
    }
}
