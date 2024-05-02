package org.mycorp.models.events.evreqlocalmanager;

import org.springframework.context.ApplicationEvent;

public class StationParametersPresented extends EVLocalRequestManagerEvent {
    private final int ratedVoltageValue;
    private final int maxCurrentValue;

    public StationParametersPresented(int ratedVoltageValue, int maxCurrentValue) {
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
