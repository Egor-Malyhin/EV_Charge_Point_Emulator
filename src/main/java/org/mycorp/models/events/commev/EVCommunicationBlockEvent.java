package org.mycorp.models.events.commev;

import org.mycorp.commev.EVCommunicationBlock;
import org.springframework.context.ApplicationEvent;

public abstract class EVCommunicationBlockEvent extends ApplicationEvent {
    public EVCommunicationBlockEvent() {
        super(EVCommunicationBlock.class);
    }
}
