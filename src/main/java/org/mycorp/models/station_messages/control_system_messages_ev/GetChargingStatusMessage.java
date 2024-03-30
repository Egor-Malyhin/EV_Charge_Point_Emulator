package org.mycorp.models.station_messages.control_system_messages_ev;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageClassification;

import static org.mycorp.models.station_messages.StationMessageClassification.GET_CHARGING_STATUS;

public class GetChargingStatusMessage extends StationMessage {
    public GetChargingStatusMessage() {
        super(GET_CHARGING_STATUS);
    }
}
