package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.MeterValues;

public class MeterValuesToCSMS extends MeterValuesPresent {
    public MeterValuesToCSMS(Object source, MeterValues meterValues) {
        super(source, meterValues);
    }
}
