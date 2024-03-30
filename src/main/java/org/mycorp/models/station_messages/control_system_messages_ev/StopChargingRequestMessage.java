package org.mycorp.models.station_messages.control_system_messages_ev;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageClassification;

import static org.mycorp.models.station_messages.StationMessageClassification.STOP_CHARGING_REQUEST;

public class StopChargingRequestMessage extends StationMessage {
    public StopChargingRequestMessage() {
        super(STOP_CHARGING_REQUEST);
    }
}
