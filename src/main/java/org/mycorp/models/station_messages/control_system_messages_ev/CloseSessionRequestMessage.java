package org.mycorp.models.station_messages.control_system_messages_ev;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageClassification;

import static org.mycorp.models.station_messages.StationMessageClassification.CLOSE_SESSION_REQUEST;

public class CloseSessionRequestMessage extends StationMessage {
    public CloseSessionRequestMessage() {
        super(CLOSE_SESSION_REQUEST);
    }
}
