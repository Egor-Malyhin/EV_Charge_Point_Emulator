package org.mycorp.ev_communication.message_handlers;

import org.mycorp.models.station_events.ev_comm_events.EVStartSessionRequest;
import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.req.SessionSetupReq;
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
