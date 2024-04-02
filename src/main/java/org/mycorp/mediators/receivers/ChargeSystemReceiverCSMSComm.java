package org.mycorp.mediators.receivers;

import org.mycorp.charge_control.interfaces.ChargeControlInterfaceCSMS;
import org.mycorp.models.station_messages.StationMessage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mycorp.models.StationStateEnum.START_CHARGING;

public class ChargeSystemReceiverCSMSComm implements Receiver {
    private final ChargeControlInterfaceCSMS chargeControlInterfaceCSMS;
    @Autowired
    public ChargeSystemReceiverCSMSComm(ChargeControlInterfaceCSMS chargeControlInterfaceCSMS) {
        this.chargeControlInterfaceCSMS = chargeControlInterfaceCSMS;
    }

    @Override
    public void receiveMessage(StationMessage message) {
        switch(message.getDescription()){
            case AUTHORIZED:
                chargeControlInterfaceCSMS.authorized();
                break;
            case UNAUTHORIZED:
                chargeControlInterfaceCSMS.unauthorized();
                break;
            case START_CHARGING:
                chargeControlInterfaceCSMS.startCharging();
                break;
            case GET_AVAILABLE:
                chargeControlInterfaceCSMS.getAvailable();
        }
    }
}
