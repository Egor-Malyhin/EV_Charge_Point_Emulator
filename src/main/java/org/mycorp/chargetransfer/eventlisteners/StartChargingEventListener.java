package org.mycorp.chargetransfer.eventlisteners;

import org.mycorp.chargetransfer.ChargeTransferBlockInterface;
import org.mycorp.models.events.stateoperator.StartCharging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartChargingEventListener extends ChargeTransferEventListener<StartCharging>{
    @Autowired
    protected StartChargingEventListener(ChargeTransferBlockInterface chargeTransferBlockInterface) {
        super(chargeTransferBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(StartCharging stationEvent) {
        chargeTransferBlockInterface.startChargeTransfer();
    }
}
