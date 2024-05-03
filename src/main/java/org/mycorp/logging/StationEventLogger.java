package org.mycorp.logging;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//Logging is performed as output to the console.
//The event call is logged by different modules,
//the name of the event is displayed, as well as the name of the class that caused the event.
//AOP is used for logging, in particular spring-aop.
//After calling the publishEvent(ApplicationEvent event) method,
//the logEvent(Object event) method is called.
@Component
@Aspect
public class StationEventLogger {
    private static final Logger logger = LoggerFactory.getLogger(StationEventLogger.class);

    /*
    @Before("execution(* org.springframework.context.ApplicationEventPublisher.publishEvent(Object)) && args(event)")
    public void logEvent(ApplicationEvent event) {
        logger.info("Event published: " + event.getClass().getName() + " by: " + event.getSource().getClass().getSimpleName());
    }*/

    @AfterThrowing(pointcut = "execution(* org.mycorp.*.*.*(..))", throwing = "ex")
    public void logException(Throwable ex) {
        logger.error("Exception caught: ", ex);
    }
}
