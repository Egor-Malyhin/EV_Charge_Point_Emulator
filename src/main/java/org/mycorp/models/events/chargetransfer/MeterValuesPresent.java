package org.mycorp.models.events.chargetransfer;

import lombok.Getter;
import org.mycorp.models.MeterValues;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class MeterValuesPresent extends ApplicationEvent {
    private final MeterValues meterValues;

    public MeterValuesPresent(Object source, MeterValues meterValues) {
        super(source);
        this.meterValues = meterValues;
    }

}
