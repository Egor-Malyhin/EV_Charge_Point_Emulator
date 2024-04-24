package org.mycorp.models.events.evreqlocalmanager;

import org.springframework.context.ApplicationEvent;

public class StationParametersPresented extends ApplicationEvent {
    private final int ratedVoltageValue;
    private final int maxCurrentValue;

    public StationParametersPresented(Object source, int ratedVoltageValue, int maxCurrentValue) {
        super(source);
        this.ratedVoltageValue = ratedVoltageValue;
        this.maxCurrentValue = maxCurrentValue;
    }

    public int getRatedVoltageValue() {
        return ratedVoltageValue;
    }

    public int getMaxCurrentValue() {
        return maxCurrentValue;
    }
}
