package org.mycorp.commev.messagehandlers;

import org.mycorp.models.events.commev.EVPowerDeliveryRequest;
import org.mycorp.models.events.common.StopCharging;
import org.mycorp.models.messages.v2g.V2GBodyAbstractType;
import org.mycorp.models.messages.v2g.req.PowerDeliveryReq;
import org.mycorp.models.messages.v2g.types.ChargeProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PowerDeliveryHandler extends V2GMessageHandlerImpl {
    @Autowired
    protected PowerDeliveryHandler(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void handleMessage(V2GBodyAbstractType v2gMessageBody) {
        ChargeProgress chargeProgress = ((PowerDeliveryReq) v2gMessageBody).getChargeProgress();
        switch (chargeProgress) {
            case START:
                applicationEventPublisher.publishEvent(new EVPowerDeliveryRequest(this));
                break;
            case STOP:
                applicationEventPublisher.publishEvent(new StopCharging(this, "EVCommunicationBlock"));
        }
    }


}
