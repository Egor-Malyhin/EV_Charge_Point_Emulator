package org.mycorp.models.events.commev;

import lombok.Getter;
import org.mycorp.models.events.StationEvent;

@Getter
public class EVStartSessionRequest extends StationEvent {
    private final String idTag;

    public EVStartSessionRequest(Object source, String idTag) {
        super(source);
        this.idTag = idTag;
    }
}
