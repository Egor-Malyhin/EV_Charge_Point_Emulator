package org.mycorp.stationeventlistener;

import org.springframework.context.ApplicationEvent;

public interface StationEventListener<T extends ApplicationEvent> {
    void listenEvent(T stationEvent);
}
