package org.mycorp.ev_communication.message_handlers;

import org.mycorp.models.station_events.ev_comm_events.EVSessionStopRequest;
import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.req.SessionStopReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SessionStopHandler extends V2GMessageHandlerImpl{
    @Autowired
    protected SessionStopHandler(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void handleMessage(V2GBodyAbstractType v2gMessageBody) {
        applicationEventPublisher.publishEvent(new EVSessionStopRequest(this));
    }
}
