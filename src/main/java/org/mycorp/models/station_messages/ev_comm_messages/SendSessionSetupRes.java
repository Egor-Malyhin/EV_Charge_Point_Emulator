package org.mycorp.models.station_messages.ev_comm_messages;

import static org.mycorp.models.station_messages.StationMessageDescription.SEND_SESSION_SETUP_RES;

public class SendSessionSetupRes extends MessageFromEVComm {
    private final boolean condition;

    public SendSessionSetupRes(boolean condition) {
        super(SEND_SESSION_SETUP_RES);
        this.condition = condition;
    }

    public boolean isCondition() {
        return condition;
    }
}
