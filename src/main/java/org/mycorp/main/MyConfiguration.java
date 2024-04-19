package org.mycorp.main;

import org.mycorp.csms_communication.message_handlers.OCPPMessageHandler;
import org.mycorp.csms_communication.message_handlers.CoreProfileOCPPMessageHandler;
import org.mycorp.ev_communication.message_handlers.*;
import org.mycorp.mediators.Mediator;
import org.mycorp.mediators.MediatorChargeControlSystem;
import org.mycorp.mediators.MediatorImpl;
import org.mycorp.mediators.senders.Sender;
import org.mycorp.mediators.senders.SenderChargeControlSystem;
import org.mycorp.mediators.senders.SenderImpl;
import org.mycorp.models.ocpp_messages.OCPPMessageProfiles;
import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.V2GMessage;
import org.mycorp.models.v2g_messages.V2GMessagesClassification;
import org.mycorp.models.v2g_messages.req.SessionSetupReq;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

import static org.mycorp.models.ocpp_messages.OCPPMessageProfiles.CORE_PROFILE;
import static org.mycorp.models.v2g_messages.V2GMessagesClassification.*;

@Configuration
@ComponentScan(basePackages = {"org.mycorp"})
public class MyConfiguration {
    @Bean
    public Map<OCPPMessageProfiles, OCPPMessageHandler> ocppMessageOperatorMap(CoreProfileOCPPMessageHandler coreProfileOCPPMessageHandler) {
        Map<OCPPMessageProfiles, OCPPMessageHandler> ocppMessageOperatorsMap = new HashMap<>();
        ocppMessageOperatorsMap.put(CORE_PROFILE, coreProfileOCPPMessageHandler);
        return ocppMessageOperatorsMap;
    }

    @Bean
    public Map<V2GMessagesClassification, V2GMessageHandler> v2GMessageHandlersMap(
            ChargeParameterDiscoveryHandler chargeParameterDiscoveryHandler,
            ChargingStatusHandler chargingStatusHandler,
            PowerDeliveryHandler powerDeliveryHandler,
            SessionSetupHandler sessionSetupHandler,
            SessionStopHandler sessionStopHandler
    ) {
        Map<V2GMessagesClassification, V2GMessageHandler> v2GMessageHandlersMap = new HashMap<>();
        v2GMessageHandlersMap.put(SESSION_SETUP_REQ, sessionSetupHandler);
        v2GMessageHandlersMap.put(CHARGE_PARAMETER_DISCOVERY_REQ, chargeParameterDiscoveryHandler);
        v2GMessageHandlersMap.put(POWER_DELIVERY_REQ, powerDeliveryHandler);
        v2GMessageHandlersMap.put(CHARGING_STATUS_REQ, chargingStatusHandler);
        v2GMessageHandlersMap.put(SESSION_STOP_REQ, sessionStopHandler);
        return v2GMessageHandlersMap;
    }

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
