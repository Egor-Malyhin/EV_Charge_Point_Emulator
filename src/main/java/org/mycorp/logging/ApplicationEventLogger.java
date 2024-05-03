package org.mycorp.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationEventLogger implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("Event published: " + event.getClass().getSimpleName() + " by: " + event.getSource().getClass().getSimpleName());
    }
}
