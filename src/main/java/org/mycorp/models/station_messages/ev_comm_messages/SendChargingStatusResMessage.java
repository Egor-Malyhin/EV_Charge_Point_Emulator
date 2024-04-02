package org.mycorp.models.station_messages.ev_comm_messages;

import org.mycorp.models.MeterValues;

import static org.mycorp.models.station_messages.StationMessageDescription.SEND_CHARGING_STATUS_RES;

public class SendChargingStatusResMessage extends MessageFromEVComm {
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
