package org.mycorp.models.events.evreqlocalmanager;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StationParametersPresented extends ApplicationEvent {
    private final int ratedVoltageValue;
    private final int maxCurrentValue;

    public StationParametersPresented(Object source, int ratedVoltageValue, int maxCurrentValue) {
        super(source);
        this.ratedVoltageValue = ratedVoltageValue;
        this.maxCurrentValue = maxCurrentValue;
    }
}
