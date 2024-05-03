package org.mycorp.models.events.chargetransfer;

import lombok.Getter;
import org.mycorp.models.MeterValues;
import org.mycorp.models.events.StationEvent;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class MeterValuesPresent extends StationEvent {
    private final MeterValues meterValues;

    public MeterValuesPresent(Object source, MeterValues meterValues) {
        super(source);
        this.meterValues = meterValues;
    }

}
