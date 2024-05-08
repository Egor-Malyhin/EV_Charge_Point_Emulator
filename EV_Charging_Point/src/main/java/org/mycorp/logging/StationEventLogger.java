package org.mycorp.logging;

import lombok.extern.slf4j.Slf4j;
import org.mycorp.models.events.StationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StationEventLogger implements ApplicationListener<StationEvent> {
    @Override
    public void onApplicationEvent(StationEvent event) {
        log.info("Event published: " + event.getClass().getSimpleName() + " by: " + event.getSource().getClass().getSimpleName());
    }
}
