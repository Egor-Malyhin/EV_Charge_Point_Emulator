package org.mycorp.models.station_messages.ev_comm_messages;

import static org.mycorp.models.station_messages.StationMessageDescription.SEND_SESSION_STOP_RES;

public class SendSessionStopResMessage extends MessageFromEVComm {
    public SendSessionStopResMessage() {
        super(SEND_SESSION_STOP_RES);
    }
}
