package org.mycorp.main;

import org.mycorp.mediators.Mediator;
import org.mycorp.mediators.MediatorChargeControlSystem;
import org.mycorp.mediators.MediatorImpl;
import org.mycorp.mediators.senders.Sender;
import org.mycorp.mediators.senders.SenderChargeControlSystem;
import org.mycorp.mediators.senders.SenderImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"org.mycorp"})
public class MyConfiguration {
    @Bean
    @Scope("singleton")
    public MediatorImpl mediatorImpl() {
        return new MediatorImpl();
    }

    @Bean
    @Scope("prototype")
    public Sender sender(@Qualifier("mediatorImpl") Mediator mediator) {
        return new SenderImpl(mediator);
    }

    @Bean
    @Scope("prototype")
    public SenderChargeControlSystem senderChargeControlSystem(@Qualifier("mediatorImpl") MediatorChargeControlSystem mediatorChargeControlSystem) {
        return new SenderChargeControlSystem(mediatorChargeControlSystem);
    }
}
