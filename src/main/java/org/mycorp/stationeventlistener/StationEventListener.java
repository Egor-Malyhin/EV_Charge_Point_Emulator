package org.mycorp.stationeventlistener;

import org.mycorp.models.events.StationEvent;
import org.springframework.context.ApplicationEvent;

//Implement this interface in event listeners classes
//Don't forget to annotate the method listenEvent(T stationEvent) with @EventListener in implementation.
public interface StationEventListener<T extends StationEvent> {
    void listenEvent(T stationEvent);
}
