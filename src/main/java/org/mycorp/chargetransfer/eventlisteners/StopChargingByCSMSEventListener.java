package org.mycorp.chargetransfer.eventlisteners;

import org.mycorp.chargetransfer.ChargeTransferBlockInterface;
import org.mycorp.models.events.commcsms.StopChargingByCSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StopChargingByCSMSEventListener extends ChargeTransferEventListener<StopChargingByCSMS> {
    @Autowired
    protected StopChargingByCSMSEventListener(ChargeTransferBlockInterface chargeTransferBlockInterface) {
        super(chargeTransferBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(StopChargingByCSMS stationEvent) {
        chargeTransferBlockInterface.stopChargeTransfer(stationEvent.getShutdownInitiator());
        stationEvent.getCsmsLatch().countDown();
    }
}
