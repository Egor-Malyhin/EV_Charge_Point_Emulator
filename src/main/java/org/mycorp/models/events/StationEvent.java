package org.mycorp.models.events;

import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

//Just a wrapper over Spring ApplicationEvent.
//Need for logging.
//If you want to add some event extend this class.
public abstract class StationEvent extends ApplicationEvent {
    public StationEvent(Object source) {
        super(source);
    }
}
