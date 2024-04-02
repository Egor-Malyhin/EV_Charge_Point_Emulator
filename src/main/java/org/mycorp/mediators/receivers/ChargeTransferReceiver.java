package org.mycorp.mediators.receivers;

import org.mycorp.charge_transfer.ChargeTransferBlockInterfaceImpl;
import org.mycorp.models.MeterValues;
import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.control_system_messages_charge_transfer.SetChargeMessage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mycorp.models.station_messages.StationMessageDescription.SET_CHARGE;

public class ChargeTransferReceiver implements Receiver {
    private final ChargeTransferBlockInterfaceImpl chargeTransferBlockInterface;
    @Autowired
    public ChargeTransferReceiver(ChargeTransferBlockInterfaceImpl chargeTransferBlockInterface) {
        this.chargeTransferBlockInterface = chargeTransferBlockInterface;
    }
    @Override
    public void receiveMessage(StationMessage message) {
        if(message.getDescription() == SET_CHARGE)
            chargeTransferBlockInterface.setCharge(((SetChargeMessage) message).getCharge());
    }
    public MeterValues receiveMeterValues(){
        return chargeTransferBlockInterface.getMeterValues();
    }
}
