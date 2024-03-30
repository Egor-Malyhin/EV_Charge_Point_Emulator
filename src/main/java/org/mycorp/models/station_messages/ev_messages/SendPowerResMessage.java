package org.mycorp.models.station_messages.ev_messages;

import org.mycorp.models.station_messages.StationMessage;

import static org.mycorp.models.station_messages.StationMessageClassification.SEND_POWER_RES;

public class SendPowerResMessage extends StationMessage {
    private final boolean canCharging;
    public SendPowerResMessage(boolean canCharging) {
        super(SEND_POWER_RES);
        this.canCharging = canCharging;
    }

    public boolean isCanCharging() {
        return canCharging;
    }
}
