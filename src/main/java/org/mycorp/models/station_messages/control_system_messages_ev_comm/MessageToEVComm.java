package org.mycorp.models.station_messages.control_system_messages_ev_comm;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageDescription;

import static org.mycorp.models.station_messages.StationMessageTypes.MESSAGE_TO_EV_COMM;

public abstract class MessageToEVComm extends StationMessage {
    public MessageToEVComm(StationMessageDescription description) {
        super(MESSAGE_TO_EV_COMM, description);
    }
}
