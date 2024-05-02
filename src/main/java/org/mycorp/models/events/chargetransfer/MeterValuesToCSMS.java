package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.MeterValues;
import org.springframework.context.ApplicationEvent;

public class MeterValuesToCSMS extends ChargeTransferEvent {
    private final MeterValues meterValues;

    public MeterValuesToCSMS(MeterValues meterValues) {
        this.meterValues = meterValues;
    }


    public MeterValues getMeterValues() {
        return meterValues;
    }
}
