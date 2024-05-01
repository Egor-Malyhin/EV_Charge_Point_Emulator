package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.commev.messagebuilders.SessionSetupResBuilder;
import org.mycorp.models.events.commcsms.EVAuthorized;
import org.mycorp.models.messages.v2g.types.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static org.mycorp.models.messages.v2g.types.ResponseCode.FAILED;
import static org.mycorp.models.messages.v2g.types.ResponseCode.OK_NewSessionEstablished;

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
        evCommunicationBlockInterface.sendMessage(buildMessage(new SessionSetupResBuilder(responseCode)));
    }
}
