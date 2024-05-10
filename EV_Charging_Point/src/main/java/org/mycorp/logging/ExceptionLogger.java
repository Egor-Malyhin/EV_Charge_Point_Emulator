package org.mycorp.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//AOP is used for logging, in particular spring-aop.
//After throwing the exception
//the logException(Throwable ex) method is called.

//When expanding the application,
//keep in mind that exceptions caught within a catch block are not logged,
//so after handling the exception,
//it's necessary to rethrow it further down the stack
//using RuntimeException(e) so that it gets logged.
@Slf4j
@Component
@Aspect
public class ExceptionLogger {
    @AfterThrowing(pointcut = "execution(* org.mycorp.*.*.*(..))", throwing = "ex")
    public void logException(Throwable ex) {
        log.warn("Exception caught: ", ex);
    }
}
