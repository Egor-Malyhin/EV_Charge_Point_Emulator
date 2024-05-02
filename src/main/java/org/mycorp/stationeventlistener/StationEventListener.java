package org.mycorp.stationeventlistener;

import org.springframework.context.ApplicationEvent;

//Implement this interface in event listeners classes
//Don't forget to annotate the method listenEvent(T stationEvent) with @EventListener in implementation.
public interface StationEventListener<T extends ApplicationEvent> {
    void listenEvent(T stationEvent);
}
