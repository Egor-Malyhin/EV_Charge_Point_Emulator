package org.mycorp.models.station_messages.charge_transfer_messages;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageDescription;

import static org.mycorp.models.station_messages.StationMessageTypes.MESSAGE_FROM_CHARGE_TRANSFER;

public abstract class MessageFromChargeTransfer extends StationMessage {
    public MessageFromChargeTransfer(StationMessageDescription description) {
        super(MESSAGE_FROM_CHARGE_TRANSFER, description);
    }
}
