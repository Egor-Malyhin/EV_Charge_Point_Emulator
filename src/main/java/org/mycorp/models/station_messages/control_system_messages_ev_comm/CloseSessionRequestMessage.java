package org.mycorp.models.station_messages.control_system_messages_ev_comm;

import static org.mycorp.models.station_messages.StationMessageDescription.CLOSE_SESSION_REQUEST;

public class CloseSessionRequestMessage extends MessageToEVComm {
    public CloseSessionRequestMessage() {
        super(CLOSE_SESSION_REQUEST);
    }
}
