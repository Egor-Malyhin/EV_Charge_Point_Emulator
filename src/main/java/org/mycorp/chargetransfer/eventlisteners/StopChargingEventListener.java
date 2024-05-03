package org.mycorp.chargetransfer.eventlisteners;

import org.mycorp.chargetransfer.ChargeTransferBlockInterface;
import org.mycorp.models.events.common.StopCharging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StopChargingEventListener extends ChargeTransferEventListener<StopCharging> {
    @Autowired
    protected StopChargingEventListener(ChargeTransferBlockInterface chargeTransferBlockInterface) {
        super(chargeTransferBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(StopCharging stationEvent) {
        chargeTransferBlockInterface.stopChargeTransfer(stationEvent.getRequesterBlock());
    }
}
