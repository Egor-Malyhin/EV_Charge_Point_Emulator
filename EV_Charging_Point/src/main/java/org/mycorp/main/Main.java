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

        ExecutorService mainExecutor = Executors.newSingleThreadExecutor();

        //The code that will be executed before shutting down the application
        //It is needed to release resources occupied during the initialization of mainExecutor
        Runtime.getRuntime().addShutdownHook(new Thread(mainExecutor::shutdown));

        mainExecutor.submit(context.getBean(CSMSCommunicationBlock.class));
        context.getBean(EVCommunicationBlock.class).initializeServer();
    }
}

