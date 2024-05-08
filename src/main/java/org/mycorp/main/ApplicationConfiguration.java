package org.mycorp.main;

import eu.chargetime.ocpp.JSONClient;
import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.CSMSMeterValuesPresenter;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.EVMeterValuesPresenter;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.MeterValuesPresenter;
import org.mycorp.commcsms.CSMSCommunicationBlockClientHandler;
import org.mycorp.commcsms.messagehandlers.*;
import org.mycorp.commev.messagehandlers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.HashMap;
import java.util.Map;

//Core Configuration class of an application.
//Add beans here that cannot be configured via @Component.
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"org.mycorp"})
public class ApplicationConfiguration {
    @Bean
    public String webSocketServerWS() {
        return "ws://localhost:8887";
    }

    @Bean
    public ClientCoreProfile clientCoreProfile(CSMSCommunicationBlockClientHandler csmsCommunicationBlockClientHandler) {
        return new ClientCoreProfile(csmsCommunicationBlockClientHandler);
    }

    @Bean
    public JSONClient jsonClient(ClientCoreProfile clientCoreProfile) {
        return new JSONClient(clientCoreProfile);
    }

    @Bean("ocppConfirmationHandlerMap")
    public Map<String, OCPPConfirmationHandler> ocppConfirmationHandlerMap(
            AuthorizeConfHandler authorizeConfHandler,
            StartTransactionConfHandler startTransactionConfHandler,
            StopTransactionConfHandler stopTransactionConfHandler,
            BootNotificationConfHandler bootNotificationConfHandler,
            StatusNotificationConfHandler statusNotificationConfHandler,
            MeterValuesConfHandler meterValuesConfHandler
    ) {
        Map<String, OCPPConfirmationHandler> ocppConfirmationHandlerMap = new HashMap<>();
        ocppConfirmationHandlerMap.put("AuthorizeConfirmation", authorizeConfHandler);
        ocppConfirmationHandlerMap.put("StartTransactionConfirmation", startTransactionConfHandler);
        ocppConfirmationHandlerMap.put("StopTransactionConfirmation", stopTransactionConfHandler);
        ocppConfirmationHandlerMap.put("BootNotificationConfirmation", bootNotificationConfHandler);
        ocppConfirmationHandlerMap.put("StatusNotificationConfirmation", statusNotificationConfHandler);
        ocppConfirmationHandlerMap.put("MeterValuesConfirmation", meterValuesConfHandler);
        return ocppConfirmationHandlerMap;
    }

    @Bean("v2gMessageHandlersMap")
    public Map<String, V2GMessageHandler> v2GMessageHandlersMap(
            ChargeParameterDiscoveryHandler chargeParameterDiscoveryHandler,
            ChargingStatusHandler chargingStatusHandler,
            PowerDeliveryHandler powerDeliveryHandler,
            SessionSetupHandler sessionSetupHandler,
            SessionStopHandler sessionStopHandler
    ) {
        Map<String, V2GMessageHandler> v2GMessageHandlersMap = new HashMap<>();
        v2GMessageHandlersMap.put("SessionSetupReq", sessionSetupHandler);
        v2GMessageHandlersMap.put("ChargeParameterDiscoveryReq", chargeParameterDiscoveryHandler);
        v2GMessageHandlersMap.put("PowerDeliveryReq", powerDeliveryHandler);
        v2GMessageHandlersMap.put("ChargingStatusReq", chargingStatusHandler);
        v2GMessageHandlersMap.put("SessionStopReq", sessionStopHandler);
        return v2GMessageHandlersMap;
    }

    @Bean("meterValuesPresenterMap")
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
