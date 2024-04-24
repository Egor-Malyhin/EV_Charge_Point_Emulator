package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.MeterValues;
import org.springframework.context.ApplicationEvent;

public class MeterValuesToEV extends ApplicationEvent {
    private final MeterValues meterValues;
    public MeterValuesToEV(Object source, MeterValues meterValues) {
        super(source);
        this.meterValues = meterValues;
    }

    public MeterValues getMeterValues() {
        return meterValues;
    }
}
