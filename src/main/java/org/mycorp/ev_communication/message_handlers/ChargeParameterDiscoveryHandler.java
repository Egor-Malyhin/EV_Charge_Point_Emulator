package org.mycorp.ev_communication.message_handlers;

import org.mycorp.models.Charge;
import org.mycorp.models.station_events.ev_comm_events.EVChargeParameterRequest;
import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.req.ChargeParameterDiscoveryReq;
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
        String unit = ((ChargeParameterDiscoveryReq) v2gMessageBody).getAcEvChargeParameter().geteАmount().getUnit();
        int value = ((ChargeParameterDiscoveryReq) v2gMessageBody).getAcEvChargeParameter().geteАmount().getValue();
        Charge charge = new Charge(unit, value);

        applicationEventPublisher.publishEvent(new EVChargeParameterRequest(this, charge));
    }
}
