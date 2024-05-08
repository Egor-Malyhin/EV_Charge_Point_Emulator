package org.mycorp.stationeventpublisher;

import org.springframework.context.ApplicationEventPublisher;

//Extend this class if your class needs to publish StationEvent
//Don't forget to annotate the subclass with @Component,
//and annotate the constructor that calls the superclass constructor with @Autowired.
public abstract class StationEventPublisher {
    protected final ApplicationEventPublisher applicationEventPublisher;

    protected StationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
