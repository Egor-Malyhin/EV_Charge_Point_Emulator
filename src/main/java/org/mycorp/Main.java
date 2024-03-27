package org.mycorp;

import org.mycorp.charge_control.*;
import org.mycorp.charge_transfer.ChargeTransferBlock;
import org.mycorp.charge_transfer.ChargeTransferBlockInterface;
import org.mycorp.charge_transfer.ChargeTransferBlockInterfaceImpl;
import org.mycorp.csms_communication.CSMSCommunicationBlock;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterface;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterfaceImpl;
import org.mycorp.ev_communication.EVCommunicationBlockHandler;
import org.mycorp.ev_communication.EvCommunicationBlock;
import org.mycorp.ev_communication.EvCommunicationBlockInterface;
import org.mycorp.ev_communication.XMLConverter;
import org.mycorp.ev_communication.message_builders.MessageBuildersDirector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        ChargeControlSystem system = context.getBean(ChargeControlSystem.class);

        ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
        mainExecutor.submit(system);
    }
}


@Configuration
class MyConfiguration {
    @Bean
    ChargeTransferBlock chargeTransferBlock(){
        return new ChargeTransferBlock();
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
    MeterValuesSender meterValuesSender(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ChargeTransferBlockInterface chargeTransferBlockInterface){
        return new MeterValuesSender(csmsCommunicationBlockInterface, chargeTransferBlockInterface);
    }

    @Bean
    public ChargeControlSystem chargeControlSystem(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ChargeTransferBlockInterface chargeTransferBlockInterface, EvCommunicationBlockInterface evCommunicationBlockInterface, InitSystem initSystem) {
        return new ChargeControlSystem( csmsCommunicationBlockInterface, evCommunicationBlockInterface, initSystem, chargeTransferBlockInterface);
    }

    @Bean
    public ChargeControlInterfaceCSMS chargeControlInterfaceCSMS(ChargeControlSystem system){
        return new ChargeControlInterfaceCSMSImpl(system);
    }

    @Bean
    public InitSystem initSystem(EvCommunicationBlock evCommunicationBlock, ChargeTransferBlock chargeTransferBlock, MeterValuesSender meterValuesSender){
        return new InitSystem(evCommunicationBlock, chargeTransferBlock, meterValuesSender);
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
    public EvCommunicationBlock evCommunicationBlock(EVCommunicationBlockHandler evCommunicationBlockHandler){
        return new EvCommunicationBlock(evCommunicationBlockHandler);
    }
}
