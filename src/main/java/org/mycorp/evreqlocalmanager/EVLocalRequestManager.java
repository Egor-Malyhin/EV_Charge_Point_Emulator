package org.mycorp.evreqlocalmanager;

import org.mycorp.models.events.StationEvent;
import org.mycorp.stationeventlistener.StationEventListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;


//This module contains the logic for processing those requests from an electric vehicle
//that do not require interaction with other modules of the program to respond to.

//This logic has been moved to a separate module so as not to spoil
//the vertical architecture of the commev module.

//Extend this class if you need to process an additional minor request from EV.
public abstract class EVLocalRequestManager<T extends StationEvent> implements StationEventListener<T> {
    protected final ApplicationEventPublisher applicationEventPublisher;

    protected EVLocalRequestManager(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
