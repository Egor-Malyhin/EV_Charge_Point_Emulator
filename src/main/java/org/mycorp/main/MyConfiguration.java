package org.mycorp.main;

import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.CSMSMeterValuesPresenter;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.EVMeterValuesPresenter;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.MeterValuesPresenter;
import org.mycorp.chargetransfer.eventpublishers.stopcharging.StopChargingByCSMSPublisher;
import org.mycorp.chargetransfer.eventpublishers.stopcharging.StopChargingByEVPublisher;
import org.mycorp.chargetransfer.eventpublishers.stopcharging.StopChargingByStationPublisher;
import org.mycorp.chargetransfer.eventpublishers.stopcharging.StopChargingEventPublisher;
import org.mycorp.commcsms.message_handlers.OCPPMessageHandler;
import org.mycorp.commcsms.message_handlers.CoreProfileOCPPMessageHandler;
import org.mycorp.commev.messagehandlers.*;
import org.mycorp.models.messages.ocpp.OCPPMessageProfiles;
import org.mycorp.models.messages.v2g.V2GMessagesClassification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.mycorp.models.messages.ocpp.OCPPMessageProfiles.CORE_PROFILE;
import static org.mycorp.models.messages.v2g.V2GMessagesClassification.*;

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
    public Map<String, StopChargingEventPublisher> stopChargingEventPublisherMap(
            StopChargingByCSMSPublisher stopChargingByCSMSPublisher,
            StopChargingByEVPublisher stopChargingByEVPublisher,
            StopChargingByStationPublisher stopChargingByStationPublisher
    ) {
        Map<String, StopChargingEventPublisher> stopChargingEventPublisherMap = new HashMap<>();
        stopChargingEventPublisherMap.put("None", stopChargingByStationPublisher);
        stopChargingEventPublisherMap.put("EVCommunicationBlock", stopChargingByEVPublisher);
        stopChargingEventPublisherMap.put("CSMSCommunicationBlock", stopChargingByCSMSPublisher);
        return stopChargingEventPublisherMap;
    }

    @Bean
    public Map<String, MeterValuesPresenter> meterValuesPresenterMap(
            CSMSMeterValuesPresenter csmsMeterValuesPresenter,
            EVMeterValuesPresenter evMeterValuesPresenter
    ) {
        Map<String, MeterValuesPresenter> meterValuesPresenterMap = new HashMap<>();
        meterValuesPresenterMap.put("EVCommunicationBlock", evMeterValuesPresenter);
        meterValuesPresenterMap.put("CSMSCommunicationBlock", csmsMeterValuesPresenter);
        return meterValuesPresenterMap;
    }
}
