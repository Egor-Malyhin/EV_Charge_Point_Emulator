package org.mycorp.models.station_messages.ev_comm_messages;

import static org.mycorp.models.station_messages.StationMessageDescription.SEND_CHARGE_PARAMETER_RES;

public class SendChargeParameterResMessage extends MessageFromEVComm {
    public SendChargeParameterResMessage() {
        super(SEND_CHARGE_PARAMETER_RES);
    }
}
