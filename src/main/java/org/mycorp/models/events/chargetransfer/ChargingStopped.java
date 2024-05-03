package org.mycorp.models.events.chargetransfer;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ChargingStopped extends ApplicationEvent {
    private final String shutdownInitiator;

    public ChargingStopped(Object source, String shutdownInitiator) {
        super(source);
        this.shutdownInitiator = shutdownInitiator;
    }

}
