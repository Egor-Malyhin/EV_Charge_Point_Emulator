package org.mycorp.chargetransfer.eventlisteners;

import org.mycorp.chargetransfer.ChargeTransferBlockInterface;
import org.mycorp.models.events.commcsms.CSMSChargingAccept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("chargeTransferCSMSChargingAcceptEventListener")
public class CSMSChargingAcceptEventListener extends ChargeTransferEventListener<CSMSChargingAccept> {
    @Autowired
    protected CSMSChargingAcceptEventListener(ChargeTransferBlockInterface chargeTransferBlockInterface) {
        super(chargeTransferBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(CSMSChargingAccept stationEvent) {
        if (stationEvent.isAccepted())
            chargeTransferBlockInterface.startChargeTransfer();
    }
}
