package org.mycorp.ev_communication.message_handlers;

import org.mycorp.models.station_events.ev_comm_events.EVChargingStatusRequest;
import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.req.ChargingStatusReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ChargingStatusHandler extends V2GMessageHandlerImpl {
    @Autowired
    protected ChargingStatusHandler(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void handleMessage(V2GBodyAbstractType v2gMessageBody) {
        applicationEventPublisher.publishEvent(new EVChargingStatusRequest(this));
    }
}
