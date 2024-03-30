package org.mycorp.models.station_messages.control_system_messages_ev;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageClassification;

import static org.mycorp.models.station_messages.StationMessageClassification.PREPARE_CHARGING;

public class PrepareChargingMessage extends StationMessage {
    private final float eAmount;
    public PrepareChargingMessage(float eAmount) {
        super(PREPARE_CHARGING);
        this.eAmount = eAmount;
    }

    public float getEAmount() {
        return eAmount;
    }
}
