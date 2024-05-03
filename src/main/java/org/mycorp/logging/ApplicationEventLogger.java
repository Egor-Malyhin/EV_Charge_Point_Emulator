package org.mycorp.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventLogger implements ApplicationListener<ApplicationEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationEventLogger.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event.getSource() instanceof Class<?> eventClass) {
            logger.info("Event published: " + event.getClass().getSimpleName() + " by: " + eventClass.getSimpleName());
        }
    }
}
