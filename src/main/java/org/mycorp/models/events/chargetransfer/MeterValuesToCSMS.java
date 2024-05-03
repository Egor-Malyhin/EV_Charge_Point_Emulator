package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.MeterValues;
import org.springframework.context.ApplicationEvent;

public class MeterValuesToCSMS extends MeterValuesPresent {
    public MeterValuesToCSMS(Object source, MeterValues meterValues) {
        super(source, meterValues);
    }
}
