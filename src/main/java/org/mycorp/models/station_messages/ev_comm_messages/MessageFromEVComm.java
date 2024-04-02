package org.mycorp.models.station_messages.ev_comm_messages;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageDescription;

import static org.mycorp.models.station_messages.StationMessageTypes.MESSAGE_FROM_EV_COMM;

public abstract class MessageFromEVComm extends StationMessage {
    public MessageFromEVComm(StationMessageDescription description) {
        super(MESSAGE_FROM_EV_COMM, description);
    }
}
