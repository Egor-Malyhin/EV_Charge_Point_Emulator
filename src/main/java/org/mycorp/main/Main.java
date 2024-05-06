package org.mycorp.main;

import org.mycorp.commcsms.CSMSCommunicationBlock;
import org.mycorp.commev.EVCommunicationBlock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//The application starts with the separate-thread execution of the components
//EVCommunicationBlock and CSMSCommunicationBlock.
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        ExecutorService mainExecutor = Executors.newFixedThreadPool(2);
        mainExecutor.submit(context.getBean(CSMSCommunicationBlock.class));
        mainExecutor.submit(context.getBean(EVCommunicationBlock.class));

        Runtime.getRuntime().addShutdownHook(new Thread(mainExecutor::shutdown));
    }
}
