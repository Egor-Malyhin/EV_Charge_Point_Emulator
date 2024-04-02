package org.mycorp;

import org.mycorp.charge_control.*;
import org.mycorp.charge_control.interfaces.ChargeControlInterfaceCSMS;
import org.mycorp.charge_control.interfaces.ChargeControlInterfaceCSMSImpl;
import org.mycorp.charge_control.interfaces.ChargeControlInterfaceEV;
import org.mycorp.charge_control.interfaces.ChargeControlInterfaceEVImpl;
import org.mycorp.charge_transfer.ChargeTransferBlock;
import org.mycorp.charge_transfer.ChargeTransferBlockInterface;
import org.mycorp.charge_transfer.ChargeTransferBlockInterfaceImpl;
import org.mycorp.csms_communication.CSMSCommunicationBlock;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterface;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterfaceImpl;
import org.mycorp.ev_communication.*;
import org.mycorp.mediators.Colleague;
import org.mycorp.mediators.Mediator;
import org.mycorp.mediators.MediatorImpl;
import org.mycorp.mediators.receivers.ChargeSystemReceiverCSMSComm;
import org.mycorp.mediators.receivers.ChargeSystemReceiverEVComm;
import org.mycorp.mediators.receivers.ChargeTransferReceiver;
import org.mycorp.mediators.senders.Sender;
import org.mycorp.mediators.senders.SenderChargeControlSystem;
import org.mycorp.mediators.senders.SenderImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);

        Mediator mediatorControlSystemEvComm = context.getBean("mediatorControlSystemEvComm", Mediator.class);
        Mediator mediatorControlSystemCsmsComm = context.getBean("mediatorControlSystemCsmsComm", Mediator.class);
        MediatorChargeTransfer mediatorChargeTransfer = context.getBean(MediatorChargeTransfer.class);

        Colleague controlSystemColleague = new Colleague();


        ChargeControlSystem system = context.getBean(ChargeControlSystem.class);

        ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
        mainExecutor.submit(system);
    }
}


@Configuration
class MyConfiguration {
    @Bean
    public ChargeTransferBlock chargeTransferBlock(){
        return new ChargeTransferBlock();
    }

    @Bean
    public CSMSCommunicationBlock csmsCommunicationBlock(){
        return new CSMSCommunicationBlock();
    }

    @Bean
    @Scope("prototype")
    public Mediator mediator(){
        return new MediatorImpl();
    }

    @Bean
    public MediatorChargeTransfer mediatorChargeTransfer(){
        return new MediatorChargeTransfer();
    }

    @Bean
    @Scope("prototype")
    public Sender sender(Mediator mediator){
        return new SenderImpl(mediator);
    }

    @Bean
    public SenderChargeControlSystem senderChargeTransfer (MediatorChargeTransfer mediatorChargeTransfer){
        return new SenderChargeControlSystem(mediatorChargeTransfer);
    }

    @Bean
    public CSMSCommunicationBlockInterface csmsCommunicationBlockInterface (CSMSCommunicationBlock csmsCommunicationBlock) {
        return new CSMSCommunicationBlockInterfaceImpl(csmsCommunicationBlock);
    }

    @Bean
    public ChargeTransferBlockInterface chargeTransferBlockInterface (ChargeTransferBlock chargeTransferBlock) {
        return new ChargeTransferBlockInterfaceImpl(chargeTransferBlock);
    }

    @Bean
    public MeterValuesSender meterValuesSender(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ChargeTransferBlockInterface chargeTransferBlockInterface){
        return new MeterValuesSender(csmsCommunicationBlockInterface, chargeTransferBlockInterface);
    }

    @Bean
    public XMLConverter xmlConverter(){
        try {
            return new XMLConverter();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public EVCommunicationBlockHandler evCommunicationBlockHandler(XMLConverter xmlConverter, ChargeControlInterfaceEV chargeControlInterfaceEV){
        return new EVCommunicationBlockHandler(xmlConverter, chargeControlInterfaceEV);
    }

    @Bean
    public ChargeControlInterfaceEV chargeControlInterfaceEV(ChargeControlSystem chargeControlSystem){
        return new ChargeControlInterfaceEVImpl(chargeControlSystem);
    }

    @Bean
    public InitSystem initSystem(EVCommunicationBlock evCommunicationBlock, ChargeTransferBlock chargeTransferBlock, CSMSCommunicationBlock csmsCommunicationBlock, MeterValuesSender meterValuesSender){
        return new InitSystem(evCommunicationBlock, chargeTransferBlock, csmsCommunicationBlock, meterValuesSender);
    }

    @Bean
    public EVCommunicationBlockInterface evCommunicationBlockInterface(EVCommunicationBlockHandler evCommunicationBlockHandler){
        return new EVCommunicationBlockInterfaceImpl(evCommunicationBlockHandler);
    }
    @Bean
    public EVCommunicationBlock evCommunicationBlock(EVCommunicationBlockHandler evCommunicationBlockHandler){
        return new EVCommunicationBlock(evCommunicationBlockHandler);
    }
    @Bean
    public ChargeControlSystem chargeControlSystem(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ChargeTransferBlockInterface chargeTransferBlockInterface, EVCommunicationBlockInterface evCommunicationBlockInterface, InitSystem initSystem) {
        return new ChargeControlSystem( csmsCommunicationBlockInterface, evCommunicationBlockInterface, initSystem, chargeTransferBlockInterface);
    }

    @Bean
    public ChargeControlInterfaceCSMS chargeControlInterfaceCSMS(ChargeControlSystem system){
        return new ChargeControlInterfaceCSMSImpl(system);
    }

    @Bean
    public ChargeTransferReceiver chargeTransferReceiver(ChargeTransferBlockInterfaceImpl chargeTransferBlockInterface){
        return new ChargeTransferReceiver(chargeTransferBlockInterface);
    }

    @Bean
    public ChargeSystemReceiverEVComm chargeSystemReceiverEv(ChargeControlInterfaceEV chargeControlInterfaceEV){
        return new ChargeSystemReceiverEVComm(chargeControlInterfaceEV);
    }

    @Bean
    public ChargeSystemReceiverCSMSComm chargeSystemReceiverCSMS(ChargeControlInterfaceCSMS chargeControlInterfaceCSMS){
        return new ChargeSystemReceiverCSMSComm(chargeControlInterfaceCSMS);
    }

    @Bean
    public Colleague colleague()

    @PostConstruct
    public void setMediatorControlSystemEvComm(){
        mediatorControlSystemEvComm().setColleague1(new Colleague());
    }
}
