package org.mycorp.models.station_messages.control_system_messages_csms_comm;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageDescription;

import static org.mycorp.models.station_messages.StationMessageTypes.MESSAGE_TO_CSMS_COMM;

public abstract class MessageToCSMSComm extends StationMessage {
    public MessageToCSMSComm(StationMessageDescription description) {
        super(MESSAGE_TO_CSMS_COMM, description);
    }
}
