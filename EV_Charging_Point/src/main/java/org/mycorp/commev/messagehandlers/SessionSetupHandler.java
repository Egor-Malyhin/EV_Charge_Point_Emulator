package org.mycorp.commev.messagehandlers;

import org.mycorp.messages.V2GBodyAbstractType;
import org.mycorp.messages.req.SessionSetupReq;
import org.mycorp.models.StationVariables;
import org.mycorp.models.events.commev.EVStartSessionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SessionSetupHandler extends V2GMessageHandlerImpl {
    @Override
    public void handleMessage(V2GBodyAbstractType v2gMessageBody) {
        String idTag = new String(((SessionSetupReq) v2gMessageBody).getEvccId());
        StationVariables.getInstance().setIdTag(idTag);
        applicationEventPublisher.publishEvent(new EVStartSessionRequest(this, idTag));
    }
}
