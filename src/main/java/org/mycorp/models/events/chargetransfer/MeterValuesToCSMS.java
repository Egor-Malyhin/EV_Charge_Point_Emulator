package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.MeterValues;

public class MeterValuesToCSMS extends ChargeTransferEvent {
    private final MeterValues meterValues;

    public MeterValuesToCSMS(MeterValues meterValues) {
        this.meterValues = meterValues;
    }


    public MeterValues getMeterValues() {
        return meterValues;
    }
}
