package org.mycorp.models.station_messages.control_system_messages_csms_comm;

import org.mycorp.models.MeterValues;

import static org.mycorp.models.station_messages.StationMessageDescription.SEND_METER_VALUES;

public class SendMeterValuesMessage extends MessageToCSMSComm {
    private final MeterValues meterValues;

    public SendMeterValuesMessage(MeterValues meterValues) {
        super(SEND_METER_VALUES);
        this.meterValues = meterValues;
    }

    public MeterValues getMeterValues() {
        return meterValues;
    }
}
