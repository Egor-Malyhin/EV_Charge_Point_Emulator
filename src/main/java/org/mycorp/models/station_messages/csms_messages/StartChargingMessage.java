package org.mycorp.models.station_messages.csms_messages;

import static org.mycorp.models.station_messages.StationMessageDescription.START_CHARGING;

public class StartChargingMessage extends MessageFromCSMS{
    public StartChargingMessage() {
        super(START_CHARGING);
    }
}
