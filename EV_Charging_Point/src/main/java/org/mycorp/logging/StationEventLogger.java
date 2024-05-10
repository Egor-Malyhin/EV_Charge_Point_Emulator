package org.mycorp.logging;

import lombok.extern.slf4j.Slf4j;
import org.mycorp.models.events.StationEvent;
import org.mycorp.stationeventlistener.StationEventListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StationEventLogger implements StationEventListener<StationEvent> {
    @Override
    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void listenEvent(StationEvent stationEvent) {
        log.info("Event published: " + stationEvent.getClass().getSimpleName() + " by: " +
                stationEvent.getSource().getClass().getSimpleName());
    }
}
