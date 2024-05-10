package org.mycorp.chargetransfer;

import lombok.extern.slf4j.Slf4j;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.MeterValuesPresenter;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.MeterValuesPresenterContext;
import org.mycorp.chargetransfer.eventpublishers.stopcharging.StopChargingEventPublisher;
import org.mycorp.models.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class ChargeTransferBlock implements ChargeTransferBlockInterface {
    private final ExecutorService chargeTransferExecutor;
    private final ChargeTransferBlockTask chargeTransferBlockTask;
    private final StopChargingEventPublisher stopChargingEventPublisher;
    private final MeterValuesPresenterContext meterValuesPresenterContext;

    @Autowired
    public ChargeTransferBlock(ChargeTransferBlockTask chargeTransferBlockTask, StopChargingEventPublisher stopChargingEventPublisher, MeterValuesPresenterContext meterValuesPresenterContext) {
        this.chargeTransferBlockTask = chargeTransferBlockTask;
        this.stopChargingEventPublisher = stopChargingEventPublisher;
        this.meterValuesPresenterContext = meterValuesPresenterContext;
        this.chargeTransferExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void startChargeTransfer() {
        CompletableFuture.runAsync(chargeTransferBlockTask, chargeTransferExecutor).thenRun(() -> {
            stopChargingEventPublisher.publishStopChargingEvent(chargeTransferBlockTask.isEmergencyStopping());
        });
    }

    @Override
    public void presentMeterValues(String requester) {
        try {
            Optional<MeterValuesPresenter> meterValuesPresenter = meterValuesPresenterContext.getMeterValuesPresenterImpl(requester);
            meterValuesPresenter.orElseThrow(() -> new ClassNotFoundException("Not supported meterValuesPresenter")).present(chargeTransferBlockTask.getMeterValues(), chargeTransferBlockTask.isRunning());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setPreparedCharge(Charge preparedCharge) {
        chargeTransferBlockTask.setPreparedCharge(preparedCharge);
    }

    @Override
    public void stopChargeTransferNormally() {
        chargeTransferBlockTask.stopNormally();
    }

    @Override
    public void stopChargeTransferEmergency() {
        chargeTransferBlockTask.stopEmergency();
    }

    @PreDestroy
    private void cleanUpThreadExecutor() {
        chargeTransferExecutor.shutdown();
    }
}
