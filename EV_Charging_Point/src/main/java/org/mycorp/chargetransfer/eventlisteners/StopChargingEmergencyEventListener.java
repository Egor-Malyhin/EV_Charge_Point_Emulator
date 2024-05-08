package org.mycorp.chargetransfer.eventlisteners;

import org.mycorp.chargetransfer.ChargeTransferBlockInterface;
import org.mycorp.models.events.common.StopChargingEmergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StopChargingEmergencyEventListener extends ChargeTransferEventListener<StopChargingEmergency> {
    @Autowired
    protected StopChargingEmergencyEventListener(ChargeTransferBlockInterface chargeTransferBlockInterface) {
        super(chargeTransferBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(StopChargingEmergency stationEvent) {
        chargeTransferBlockInterface.stopChargeTransferEmergency();
    }
}
