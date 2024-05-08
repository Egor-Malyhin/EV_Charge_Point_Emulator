package org.mycorp.commcsms;

import eu.chargetime.ocpp.feature.profile.ClientCoreEventHandler;
import eu.chargetime.ocpp.model.core.*;
import lombok.extern.slf4j.Slf4j;
import org.mycorp.models.events.common.StopChargingNormally;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

//Implementation of the ClientCoreEventHandler interface
//from the eu.chargetime Java OCA-OCPP library.
//Handles requests initiated by the CSMS.
@Slf4j
@Component
public class CSMSCommunicationBlockClientHandler implements ClientCoreEventHandler {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public CSMSCommunicationBlockClientHandler(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public RemoteStopTransactionConfirmation handleRemoteStopTransactionRequest(RemoteStopTransactionRequest remoteStopTransactionRequest) {
        log.info("Received Request from CSMS: " + remoteStopTransactionRequest.getClass().getSimpleName());
        applicationEventPublisher.publishEvent(new StopChargingNormally(this));
        log.info("Send Confirmation to CSMS: " + RemoteStopTransactionConfirmation.class.getSimpleName());
        return new RemoteStopTransactionConfirmation(RemoteStartStopStatus.Accepted);
    }

    @Override
    public ChangeAvailabilityConfirmation handleChangeAvailabilityRequest(ChangeAvailabilityRequest changeAvailabilityRequest) {
        return null;
    }

    @Override
    public GetConfigurationConfirmation handleGetConfigurationRequest(GetConfigurationRequest getConfigurationRequest) {
        return null;
    }

    @Override
    public ChangeConfigurationConfirmation handleChangeConfigurationRequest(ChangeConfigurationRequest changeConfigurationRequest) {
        return null;
    }

    @Override
    public ClearCacheConfirmation handleClearCacheRequest(ClearCacheRequest clearCacheRequest) {
        return null;
    }

    @Override
    public DataTransferConfirmation handleDataTransferRequest(DataTransferRequest dataTransferRequest) {
        return null;
    }

    @Override
    public RemoteStartTransactionConfirmation handleRemoteStartTransactionRequest(RemoteStartTransactionRequest remoteStartTransactionRequest) {
        return null;
    }

    @Override
    public ResetConfirmation handleResetRequest(ResetRequest resetRequest) {
        return null;
    }

    @Override
    public UnlockConnectorConfirmation handleUnlockConnectorRequest(UnlockConnectorRequest unlockConnectorRequest) {
        return null;
    }
}
