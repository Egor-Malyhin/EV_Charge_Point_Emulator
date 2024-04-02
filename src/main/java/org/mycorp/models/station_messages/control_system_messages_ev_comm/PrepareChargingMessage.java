package org.mycorp.models.station_messages.control_system_messages_ev_comm;

import static org.mycorp.models.station_messages.StationMessageDescription.PREPARE_CHARGING;

public class PrepareChargingMessage extends MessageToEVComm {
    private final float eAmount;

    public PrepareChargingMessage(float eAmount) {
        super(PREPARE_CHARGING);
        this.eAmount = eAmount;
    }

    public float getEAmount() {
        return eAmount;
    }
}
