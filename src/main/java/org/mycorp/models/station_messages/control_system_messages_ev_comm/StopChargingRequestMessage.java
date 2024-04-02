package org.mycorp.models.station_messages.control_system_messages_ev_comm;

import static org.mycorp.models.station_messages.StationMessageDescription.STOP_CHARGING_REQUEST;

public class StopChargingRequestMessage extends MessageToEVComm {
    public StopChargingRequestMessage() {
        super(STOP_CHARGING_REQUEST);
    }
}
