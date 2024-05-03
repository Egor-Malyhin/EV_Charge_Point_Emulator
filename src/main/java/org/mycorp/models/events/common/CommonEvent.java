package org.mycorp.models.events.common;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class CommonEvent extends ApplicationEvent {
    private final String requesterBlock;

    public CommonEvent(Object source, String requesterBlock) {
        super(source);
        this.requesterBlock = requesterBlock;
    }
}
