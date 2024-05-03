package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.MeterValues;
import org.springframework.context.ApplicationEvent;

public class MeterValuesToEV extends MeterValuesPresent{
    private final boolean isChargingOn;

    public MeterValuesToEV(Object source, MeterValues meterValues, boolean isChargingOn) {
        super(source, meterValues);
        this.isChargingOn = isChargingOn;
    }

    public boolean isChargingOn() {
        return isChargingOn;
    }
}
