package org.mycorp.models.events.common;

import lombok.Getter;
import org.mycorp.models.events.StationEvent;

@Getter
public abstract class CommonEvent extends StationEvent {
    private final String requesterBlock;

    public CommonEvent(Object source, String requesterBlock) {
        super(source);
        this.requesterBlock = requesterBlock;
    }
}
