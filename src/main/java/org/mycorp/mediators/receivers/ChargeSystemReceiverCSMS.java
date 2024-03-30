package org.mycorp.mediators.receivers;

import org.mycorp.charge_control.ChargeControlInterfaceCSMS;
import org.mycorp.models.station_messages.StationMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class ChargeSystemReceiverCSMS implements Receiver {
    private final ChargeControlInterfaceCSMS chargeControlInterfaceCSMS;
    @Autowired
    public ChargeSystemReceiverCSMS(ChargeControlInterfaceCSMS chargeControlInterfaceCSMS) {
        this.chargeControlInterfaceCSMS = chargeControlInterfaceCSMS;
    }

    @Override
    public void receiveMessage(StationMessage message) {
        switch(message.getDescription()){
            case PREPARED_CHARGING:
                chargeControlInterfaceCSMS.preparedCharging();
                break;
            case START_CHARGING:
                chargeControlInterfaceCSMS.startCharging();
                break;
            case GET_AVAILABLE:
                chargeControlInterfaceCSMS.getAvailable();
        }
    }
}
