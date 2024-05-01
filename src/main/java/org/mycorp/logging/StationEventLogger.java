package org.mycorp.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

//Logging is performed as output to the console.
//The event call is logged by different modules,
//the name of the event is displayed, as well as the name of the class that caused the event.
//AOP is used for logging, in particular spring-aop.
//After calling the publishEvent(ApplicationEvent event) method,
//the logEvent(Object event) method is called.
@Aspect
@Component
public class StationEventLogger {
    private static final Logger logger = LoggerFactory.getLogger(StationEventLogger.class);;
    @Pointcut("execution(* org.springframework.context.ApplicationEventPublisher.publishEvent(..))")
    public void publishEventPointcut() {}

    @After("publishEventPointcut() && args(event)")
    public void logEvent(Object event) {
        ApplicationEvent applicationEvent = (ApplicationEvent) event;
        logger.info("Event published: " + applicationEvent.getClass().getName() + " by: " + applicationEvent.getSource().getClass().getName());
    }
}
