package org.mycorp.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//Logging is performed as output to the console.
//The event call is logged by different modules,
//the name of the event is displayed, as well as the name of the class that caused the event.
//AOP is used for logging, in particular spring-aop.
//After calling the publishEvent(ApplicationEvent event) method,
//the logEvent(Object event) method is called.
@Slf4j
@Component
@Aspect
public class ExceptionLogger {
    @AfterThrowing(pointcut = "execution(* org.mycorp.*.*.*(..))", throwing = "ex")
    public void logException(Throwable ex) {
        log.warn("Exception caught: ", ex);
    }
}
