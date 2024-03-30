package org.mycorp.models.station_messages.ev_messages;

import org.mycorp.models.station_messages.StationMessage;

import static org.mycorp.models.station_messages.StationMessageClassification.SEND_SESSION_SETUP_RES;

public class SendSessionSetupRes extends StationMessage {
    private final boolean condition;
    public SendSessionSetupRes(boolean condition) {
        super(SEND_SESSION_SETUP_RES);
        this.condition = condition;
    }

    public boolean isCondition() {
        return condition;
    }
}
