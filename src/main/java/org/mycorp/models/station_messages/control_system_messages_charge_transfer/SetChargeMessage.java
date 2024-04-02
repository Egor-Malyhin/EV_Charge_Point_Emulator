package org.mycorp.models.station_messages.control_system_messages_charge_transfer;

import org.mycorp.models.Charge;

import static org.mycorp.models.station_messages.StationMessageDescription.SET_CHARGE;

public class SetChargeMessage extends MessageToChargeTransfer {
    private final Charge charge;

    public SetChargeMessage(Charge charge) {
        super(SET_CHARGE);
        this.charge = charge;
    }

    public Charge getCharge() {
        return charge;
    }
}
