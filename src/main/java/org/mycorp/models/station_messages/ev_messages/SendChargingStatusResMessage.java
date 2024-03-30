package org.mycorp.models.station_messages.ev_messages;

import org.mycorp.models.MeterValues;
import org.mycorp.models.station_messages.StationMessage;

import static org.mycorp.models.station_messages.StationMessageClassification.SEND_CHARGING_STATUS_RES;

public class SendChargingStatusResMessage extends StationMessage {
    private final MeterValues meterValues;
    private final boolean isChargingOn;
    public SendChargingStatusResMessage(MeterValues meterValues, boolean isChargingOn) {
        super(SEND_CHARGING_STATUS_RES);
        this.meterValues = meterValues;
        this.isChargingOn = isChargingOn;
    }

    public MeterValues getMeterValues() {
        return meterValues;
    }

    public boolean isChargingOn() {
        return isChargingOn;
    }
}
