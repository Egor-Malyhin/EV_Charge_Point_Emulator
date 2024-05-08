package org.mycorp.commev.messagehandlers;

import org.mycorp.messages.V2GBodyAbstractType;
import org.mycorp.messages.req.ChargeParameterDiscoveryReq;
import org.mycorp.models.Charge;
import org.mycorp.models.events.commev.EVChargeParameterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ChargeParameterDiscoveryHandler extends V2GMessageHandlerImpl {
    @Autowired
    protected ChargeParameterDiscoveryHandler(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void handleMessage(V2GBodyAbstractType v2gMessageBody) {
        String unit = ((ChargeParameterDiscoveryReq) v2gMessageBody).getAcEvChargeParameter().getEАmount().getUnit();
        int value = ((ChargeParameterDiscoveryReq) v2gMessageBody).getAcEvChargeParameter().getEАmount().getValue();
        Charge charge = new Charge(unit, value);

        applicationEventPublisher.publishEvent(new EVChargeParameterRequest(this, charge));
    }
}
