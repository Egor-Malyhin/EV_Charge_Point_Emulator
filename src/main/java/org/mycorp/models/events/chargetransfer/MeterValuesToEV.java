package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.MeterValues;

public class MeterValuesToEV extends ChargeTransferEvent {
    private final MeterValues meterValues;
    private final boolean isChargingOn;

    public MeterValuesToEV(MeterValues meterValues, boolean isChargingOn) {
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
