package org.mycorp.models.station_messages.charge_transfer_messages;

import static org.mycorp.models.station_messages.StationMessageDescription.STOP_CHARGING_BY_STATION;

public class StopChargingByStationMessage extends MessageFromChargeTransfer {
    public StopChargingByStationMessage() {
        super(STOP_CHARGING_BY_STATION);
    }
}
