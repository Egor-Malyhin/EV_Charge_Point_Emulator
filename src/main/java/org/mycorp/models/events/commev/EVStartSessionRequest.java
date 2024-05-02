package org.mycorp.models.events.commev;

import org.springframework.context.ApplicationEvent;

public class EVStartSessionRequest extends EVCommunicationBlockEvent {
    private final String idTag;

    public EVStartSessionRequest(String idTag) {
        this.idTag = idTag;
    }

    public String getIdTag() {
        return idTag;
    }
}
