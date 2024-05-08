package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.commev.messagefactory.V2GMessageResFactory;
import org.mycorp.models.events.commevhelper.EVDisconnectionAccept;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EVDisconnectionAcceptEventListener extends EVCommunicationBlockEventListener<EVDisconnectionAccept> {
    @Autowired
    protected EVDisconnectionAcceptEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        super(evCommunicationBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(EVDisconnectionAccept stationEvent) {
        evCommunicationBlockInterface.acceptDisconnect();
        evCommunicationBlockInterface.sendMessage(V2GMessageResFactory.createSessionStopResMessage(ResponseCode.OK));
    }
}
