package org.mycorp.main;

import org.mycorp.charge_control.ChargeControlSystem;
import org.mycorp.mediators.Mediator;
import org.mycorp.mediators.MediatorChargeControlSystem;
import org.mycorp.mediators.MediatorImpl;
import org.mycorp.mediators.receivers.*;
import org.mycorp.mediators.senders.Sender;
import org.mycorp.mediators.senders.SenderChargeControlSystem;
import org.mycorp.mediators.senders.SenderImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);

        beforeStart(context);

        ChargeControlSystem system = context.getBean(ChargeControlSystem.class);

        ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
        mainExecutor.submit(system);
    }

    public static void beforeStart(ApplicationContext context) {
        Object mediator = context.getBean("mediatorImpl");
        ((MediatorImpl) mediator).addReceiver(context.getBean(ChargeTransferReceiver.class));
        ((MediatorImpl) mediator).addReceiver(context.getBean(ChargeSystemReceiverCSMSComm.class));
        ((MediatorImpl) mediator).addReceiver(context.getBean(ChargeSystemReceiverEVComm.class));
        ((MediatorImpl) mediator).addReceiver(context.getBean(ChargeSystemReceiverChargeTransfer.class));
        ((MediatorImpl) mediator).addReceiver(context.getBean(EVCommReceiver.class));
        ((MediatorImpl) mediator).addReceiver(context.getBean(CSMSCommReceiver.class));
    }
}

