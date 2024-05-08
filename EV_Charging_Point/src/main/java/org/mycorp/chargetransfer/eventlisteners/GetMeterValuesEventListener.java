package org.mycorp.chargetransfer.eventlisteners;

import org.mycorp.chargetransfer.ChargeTransferBlockInterface;
import org.mycorp.models.events.common.GetMeterValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GetMeterValuesEventListener extends ChargeTransferEventListener<GetMeterValues> {
    @Autowired
    protected GetMeterValuesEventListener(ChargeTransferBlockInterface chargeTransferBlockInterface) {
        super(chargeTransferBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(GetMeterValues stationEvent) {
        chargeTransferBlockInterface.presentMeterValues(stationEvent.getRequesterBlock());
    }
}
