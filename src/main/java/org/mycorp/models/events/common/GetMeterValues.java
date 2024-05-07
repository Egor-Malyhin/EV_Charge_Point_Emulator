package org.mycorp.models.events.common;

import lombok.Getter;
import org.mycorp.models.events.StationEvent;

@Getter
public class GetMeterValues extends StationEvent {
    private final String requesterBlock;
    public GetMeterValues(Object source, String requesterBlock) {
        super(source);
        this.requesterBlock = requesterBlock;
    }
}
