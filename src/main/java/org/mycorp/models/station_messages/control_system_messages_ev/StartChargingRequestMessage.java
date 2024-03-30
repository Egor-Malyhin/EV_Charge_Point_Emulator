package org.mycorp.models.station_messages.control_system_messages_ev;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageClassification;

import static org.mycorp.models.station_messages.StationMessageClassification.START_CHARGING_REQUEST;

public class StartChargingRequestMessage extends StationMessage {
    public StartChargingRequestMessage() {
        super(START_CHARGING_REQUEST);
    }
}
