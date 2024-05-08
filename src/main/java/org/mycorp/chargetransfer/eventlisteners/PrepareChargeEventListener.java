package org.mycorp.chargetransfer.eventlisteners;

import org.mycorp.chargetransfer.ChargeTransferBlockInterface;
import org.mycorp.models.events.commev.EVChargeParameterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PrepareChargeEventListener extends ChargeTransferEventListener<EVChargeParameterRequest> {
    @Autowired
    protected PrepareChargeEventListener(ChargeTransferBlockInterface chargeTransferBlockInterface) {
        super(chargeTransferBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(EVChargeParameterRequest stationEvent) {
        chargeTransferBlockInterface.setCharge(stationEvent.getAmount());
    }
}
