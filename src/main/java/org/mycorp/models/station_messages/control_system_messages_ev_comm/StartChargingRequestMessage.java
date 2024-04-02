package org.mycorp.models.station_messages.control_system_messages_ev_comm;

import static org.mycorp.models.station_messages.StationMessageDescription.START_CHARGING_REQUEST;

public class StartChargingRequestMessage extends MessageToEVComm {
    public StartChargingRequestMessage() {
        super(START_CHARGING_REQUEST);
    }
}
