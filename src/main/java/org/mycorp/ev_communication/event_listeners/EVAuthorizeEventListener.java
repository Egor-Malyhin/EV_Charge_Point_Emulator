package org.mycorp.ev_communication.event_listeners;

import org.mycorp.ev_communication.EVCommunicationBlockInterface;
import org.mycorp.ev_communication.message_builders.SessionSetupResBuilder;
import org.mycorp.models.station_events.csms_comm_events.EVAuthorized;
import org.mycorp.models.v2g_messages.types.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import static org.mycorp.models.v2g_messages.types.ResponseCode.FAILED;
import static org.mycorp.models.v2g_messages.types.ResponseCode.OK_NewSessionEstablished;

public class EVAuthorizeEventListener extends EVCommunicationBlockEventListener<EVAuthorized>{
    @Autowired
    protected EVAuthorizeEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        super(evCommunicationBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(EVAuthorized stationEvent) {
        ResponseCode responseCode = stationEvent.isAuthorized() ? OK_NewSessionEstablished : FAILED;
        evCommunicationBlockInterface.sendMessage(buildMessage(new SessionSetupResBuilder(responseCode)));
    }
}
