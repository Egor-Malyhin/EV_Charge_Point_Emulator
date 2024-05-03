package org.mycorp.chargetransfer;

import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.MeterValuesPresenter;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.MeterValuesPresenterContext;
import org.mycorp.chargetransfer.eventpublishers.stopcharging.StopChargingEventPublisher;
import org.mycorp.models.Charge;
import org.mycorp.models.StationVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        CompletableFuture<Void> future = CompletableFuture.runAsync(chargeTransferBlockTask, chargeTransferExecutor);
        future.thenRun(() -> {
            StationVariables.getInstance().setMeterCurrent((int) chargeTransferBlockTask.getMeterValues().getSampledValue().get(0).getValue());
            stopChargingEventPublisher.publishStopChargingEvent(chargeTransferBlockTask.getShutdownInitiator());
        });
    }

    @Override
    public void presentMeterValues(String requester) {
        try {
            Optional<MeterValuesPresenter> meterValuesPresenter = meterValuesPresenterContext.getMeterValuesPresenterImpl(requester);
            meterValuesPresenter.orElseThrow(() -> new ClassNotFoundException("Not supported meterValuesPresenter")).present(chargeTransferBlockTask.getMeterValues(), chargeTransferBlockTask.isRunning());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCharge(Charge charge) {
        chargeTransferBlockTask.setCharge(charge);
    }

    @Override
    public void stopChargeTransfer(String shutdownInitiator) {
        chargeTransferBlockTask.stop(shutdownInitiator);
    }

    @PreDestroy
    private void cleanUpThreadExecutor() {
        chargeTransferExecutor.shutdown();
    }
}
