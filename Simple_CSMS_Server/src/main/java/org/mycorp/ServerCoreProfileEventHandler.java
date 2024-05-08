package org.mycorp;

import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.*;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

//Class that handles requests from Charge Point
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
        requestLogging(startTransactionRequest);
        log.info("New transaction started");
        return new StartTransactionConfirmation(new IdTagInfo(AuthorizationStatus.Accepted), 1);
    }

    @Override
    public StatusNotificationConfirmation handleStatusNotificationRequest(UUID uuid, StatusNotificationRequest statusNotificationRequest) {
        requestLogging(statusNotificationRequest);
        return new StatusNotificationConfirmation();
    }

    @Override
    public StopTransactionConfirmation handleStopTransactionRequest(UUID uuid, StopTransactionRequest stopTransactionRequest) {
        requestLogging(stopTransactionRequest);
        log.info("Transaction stopped");
        return new StopTransactionConfirmation();
    }

    private void requestLogging(Request request) {
        log.info("Request from ChargePoint: " + request);
    }

    private void meterValuesLogging(MeterValuesRequest meterValuesRequest) {

        for (MeterValue meter : meterValuesRequest.getMeterValue()) {
            SampledValue[] sampledValues = meter.getSampledValue();
            for (SampledValue sampled : sampledValues) {
                String value = sampled.getValue();
                String unit = sampled.getUnit();
                log.info("Current Meter Values: " + value + unit);
            }
        }
    }
}
