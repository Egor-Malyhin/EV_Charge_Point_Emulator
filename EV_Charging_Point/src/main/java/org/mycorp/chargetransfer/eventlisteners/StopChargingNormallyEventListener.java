package org.mycorp.chargetransfer.eventlisteners;

import org.mycorp.chargetransfer.ChargeTransferBlockInterface;
import org.mycorp.models.events.common.StopChargingNormally;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StopChargingNormallyEventListener extends ChargeTransferEventListener<StopChargingNormally> {
    @Autowired
    protected StopChargingNormallyEventListener(ChargeTransferBlockInterface chargeTransferBlockInterface) {
        super(chargeTransferBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(StopChargingNormally stationEvent) {
        chargeTransferBlockInterface.stopChargeTransferNormally();
    }
}
