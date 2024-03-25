package org.mycorp;

import org.mycorp.charge_control.ChargeControlInterfaceCSMS;
import org.mycorp.charge_control.ChargeControlInterfaceCSMSImpl;
import org.mycorp.charge_control.ChargeControlSystem;
import org.mycorp.charge_control.InitSystem;
import org.mycorp.charge_transfer.ChargeTransferBlock;
import org.mycorp.charge_transfer.ChargeTransferBlockInterface;
import org.mycorp.charge_transfer.ChargeTransferBlockInterfaceImpl;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterface;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterfaceImpl;
import org.mycorp.ev_communication.EVCommunicationBlockHandler;
import org.mycorp.ev_communication.EvCommunicationBlock;
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
    public ChargeControlSystem chargeControlSystem(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ChargeTransferBlockInterface chargeTransferBlockInterface) {
        return new ChargeControlSystem( csmsCommunicationBlockInterface, chargeTransferBlockInterface);
    }

    @Bean
    public ChargeControlInterfaceCSMS chargeControlInterfaceCSMS(ChargeControlSystem system){
        return new ChargeControlInterfaceCSMSImpl(system);
    }

    @Bean
    public InitSystem initSystem(EvCommunicationBlock evCommunicationBlock, ChargeTransferBlock chargeTransferBlock){
        return new InitSystem(evCommunicationBlock, chargeTransferBlock);
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
    public EVCommunicationBlockHandler evCommunicationBlockHandler(XMLConverter xmlConverter){
        return new EVCommunicationBlockHandler(xmlConverter);
    }
    @Bean
    public EvCommunicationBlock evCommunicationBlock(EVCommunicationBlockHandler evCommunicationBlockHandler){
        return new EvCommunicationBlock(evCommunicationBlockHandler);
    }
}
