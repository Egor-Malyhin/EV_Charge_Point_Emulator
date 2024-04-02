package org.mycorp.models.station_messages.ev_comm_messages;

import static org.mycorp.models.station_messages.StationMessageDescription.SEND_POWER_RES;

public class SendPowerResMessage extends MessageFromEVComm {
    private final boolean canCharging;

    public SendPowerResMessage(boolean canCharging) {
        super(SEND_POWER_RES);
        this.canCharging = canCharging;
    }

    public boolean isCanCharging() {
        return canCharging;
    }
}
