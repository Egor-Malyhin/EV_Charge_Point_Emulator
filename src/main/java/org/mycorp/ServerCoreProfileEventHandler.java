package org.mycorp;

import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.*;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
public class ServerCoreProfileEventHandler implements ServerCoreEventHandler {
    @Override
    public AuthorizeConfirmation handleAuthorizeRequest(UUID uuid, AuthorizeRequest authorizeRequest) {
        requestLogging(authorizeRequest);
        return new AuthorizeConfirmation(new IdTagInfo(AuthorizationStatus.Accepted));
    }

    @Override
    public BootNotificationConfirmation handleBootNotificationRequest(UUID uuid, BootNotificationRequest bootNotificationRequest) {
        requestLogging(bootNotificationRequest);
        return new BootNotificationConfirmation(ZonedDateTime.now(ZoneId.of("Asia/Novosibirsk")), 2, RegistrationStatus.Accepted);
    }

    @Override
    public DataTransferConfirmation handleDataTransferRequest(UUID uuid, DataTransferRequest dataTransferRequest) {
        requestLogging(dataTransferRequest);
        return new DataTransferConfirmation(DataTransferStatus.Accepted);
    }

    @Override
    public HeartbeatConfirmation handleHeartbeatRequest(UUID uuid, HeartbeatRequest heartbeatRequest) {
        requestLogging(heartbeatRequest);
        return new HeartbeatConfirmation(ZonedDateTime.now(ZoneId.of("Asia/Novosibirsk")));
    }

    @Override
    public MeterValuesConfirmation handleMeterValuesRequest(UUID uuid, MeterValuesRequest meterValuesRequest) {
        requestLogging(meterValuesRequest);
        meterValuesLogging(meterValuesRequest);
        return new MeterValuesConfirmation();
    }

    @Override
    public StartTransactionConfirmation handleStartTransactionRequest(UUID uuid, StartTransactionRequest startTransactionRequest) {
        startTransactionLogging();
        return new StartTransactionConfirmation(new IdTagInfo(AuthorizationStatus.Accepted), 1);
    }

    @Override
    public StatusNotificationConfirmation handleStatusNotificationRequest(UUID uuid, StatusNotificationRequest statusNotificationRequest) {
        statusNotificationLogging(statusNotificationRequest);
        return new StatusNotificationConfirmation();
    }

    @Override
    public StopTransactionConfirmation handleStopTransactionRequest(UUID uuid, StopTransactionRequest stopTransactionRequest) {
        stopTransactionLogging();
        return new StopTransactionConfirmation();
    }

    private void requestLogging(Request request) {
        log.info("Request from ChargePoint: " + request);
    }

    private void meterValuesLogging(MeterValuesRequest meterValuesRequest) {
        log.info("Current Meter Values: " + Arrays.toString(meterValuesRequest.getMeterValue()));
    }

    private void startTransactionLogging(){
        log.info("Transaction started");
    }

    private void stopTransactionLogging(){
        log.info("Transaction stopped");
    }

    private void statusNotificationLogging(StatusNotificationRequest statusNotificationRequest){
        log.info("Current station status: " + statusNotificationRequest.getStatus());
    }
}
