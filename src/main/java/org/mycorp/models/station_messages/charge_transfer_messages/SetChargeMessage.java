package org.mycorp.models.station_messages.charge_transfer_messages;

import org.mycorp.models.Charge;
import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageClassification;

public class SetChargeMessage extends StationMessage {
    private final Charge charge;
    public SetChargeMessage(StationMessageClassification description, Charge charge) {
        super(description);
        this.charge = charge;
    }

    public Charge getCharge() {
        return charge;
    }
}
