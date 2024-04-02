package org.mycorp.models.station_messages.control_system_messages_ev_comm;

import static org.mycorp.models.station_messages.StationMessageDescription.GET_CHARGING_STATUS;

public class GetChargingStatusMessage extends MessageToEVComm {
    public GetChargingStatusMessage() {
        super(GET_CHARGING_STATUS);
    }
}
