package org.mycorp.models.station_messages.control_system_messages_charge_transfer;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageDescription;

import static org.mycorp.models.station_messages.StationMessageTypes.MESSAGE_TO_CHARGE_TRANSFER;

public abstract class MessageToChargeTransfer extends StationMessage {
    public MessageToChargeTransfer(StationMessageDescription description) {
        super(MESSAGE_TO_CHARGE_TRANSFER, description);
    }
}
