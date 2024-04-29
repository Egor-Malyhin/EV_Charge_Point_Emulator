package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.MeterValues;
import org.springframework.context.ApplicationEvent;

public class MeterValuesToEV extends ApplicationEvent {
    private final MeterValues meterValues;
    private final boolean isChargingOn;

    public MeterValuesToEV(Object source, MeterValues meterValues, boolean isChargingOn) {
        super(source);
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
