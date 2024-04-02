package org.mycorp.models.station_messages.csms_messages;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageDescription;
import org.mycorp.models.station_messages.StationMessageTypes;

public abstract class MessageFromCSMS extends StationMessage {
    public MessageFromCSMS(StationMessageDescription description) {
        super(StationMessageTypes.MESSAGE_FROM_CSMS_COMM, description);
    }
}
