package org.mycorp.models.station_messages.ev_messages;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageClassification;

import static org.mycorp.models.station_messages.StationMessageClassification.SEND_SESSION_STOP_RES;

public class SendSessionStopResMessage extends StationMessage {
    public SendSessionStopResMessage(StationMessageClassification stationMessageClassification) {
        super(SEND_SESSION_STOP_RES);
    }
}
