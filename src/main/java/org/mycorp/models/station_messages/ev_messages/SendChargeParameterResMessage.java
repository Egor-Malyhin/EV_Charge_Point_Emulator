package org.mycorp.models.station_messages.ev_messages;

import org.mycorp.models.station_messages.StationMessage;

import static org.mycorp.models.station_messages.StationMessageClassification.SEND_CHARGE_PARAMETER_RES;

public class SendChargeParameterResMessage extends StationMessage {
    public SendChargeParameterResMessage() {
        super(SEND_CHARGE_PARAMETER_RES);
    }
}
