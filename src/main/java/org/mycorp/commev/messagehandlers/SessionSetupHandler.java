package org.mycorp.commev.messagehandlers;

import org.mycorp.models.events.commev.EVStartSessionRequest;
import org.mycorp.models.messages.v2g.V2GBodyAbstractType;
import org.mycorp.models.messages.v2g.req.SessionSetupReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SessionSetupHandler extends V2GMessageHandlerImpl {
    @Autowired
    protected SessionSetupHandler(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void handleMessage(V2GBodyAbstractType v2gMessageBody) {
        String idTag = new String(((SessionSetupReq)v2gMessageBody).getEvccId());
        applicationEventPublisher.publishEvent(new EVStartSessionRequest(this, idTag));
    }
}
