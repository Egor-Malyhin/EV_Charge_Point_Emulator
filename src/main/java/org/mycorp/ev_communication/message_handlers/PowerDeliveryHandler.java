package org.mycorp.ev_communication.message_handlers;

import org.mycorp.models.station_events.ev_comm_events.EVPowerDeliveryRequest;
import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.req.PowerDeliveryReq;
import org.mycorp.models.v2g_messages.types.ChargeProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PowerDeliveryHandler extends V2GMessageHandlerImpl{
    @Autowired
    protected PowerDeliveryHandler(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void handleMessage(V2GBodyAbstractType v2gMessageBody) {
        ChargeProgress chargeProgress = ((PowerDeliveryReq) v2gMessageBody).getChargeProgress();
        applicationEventPublisher.publishEvent(new EVPowerDeliveryRequest(this, chargeProgress));
    }
}
