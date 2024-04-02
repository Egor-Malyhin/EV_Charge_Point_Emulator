package org.mycorp.mediators.receivers;

import org.mycorp.charge_control.interfaces.ChargeControlInterfaceChargeTransfer;
import org.mycorp.models.station_messages.StationMessage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mycorp.models.station_messages.StationMessageDescription.STOP_CHARGING_BY_STATION;

public class ChargeSystemReceiverChargeTransfer implements Receiver{
    private final ChargeControlInterfaceChargeTransfer chargeControlInterfaceChargeTransfer;

    @Autowired
    public ChargeSystemReceiverChargeTransfer(ChargeControlInterfaceChargeTransfer chargeControlInterfaceChargeTransfer) {
        this.chargeControlInterfaceChargeTransfer = chargeControlInterfaceChargeTransfer;
    }

    @Override
    public void receiveMessage(StationMessage message) {
        if(message.getDescription() == STOP_CHARGING_BY_STATION)
            chargeControlInterfaceChargeTransfer.stopChargingByStation();
    }
}
