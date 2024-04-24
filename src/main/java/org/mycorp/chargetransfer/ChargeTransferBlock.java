package org.mycorp.chargetransfer;

import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.MeterValuesPresenter;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.MeterValuesPresenterContext;
import org.mycorp.chargetransfer.eventpublishers.stopcharging.StopChargingEventPublisher;
import org.mycorp.chargetransfer.eventpublishers.stopcharging.StopChargingEventPublisherContext;
import org.mycorp.models.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ChargeTransferBlock implements ChargeTransferBlockInterface{
    private final ExecutorService chargeTransferExecutor;
    private final ChargeTransferBlockTask chargeTransferBlockTask;
    private final StopChargingEventPublisherContext stopChargingEventPublisherContext;
    private final MeterValuesPresenterContext meterValuesPresenterContext;

    @Autowired
    public ChargeTransferBlock(ChargeTransferBlockTask chargeTransferBlockTask, StopChargingEventPublisherContext stopChargingEventPublisherContext, MeterValuesPresenterContext meterValuesPresenterContext) {
        this.chargeTransferBlockTask = chargeTransferBlockTask;
        this.stopChargingEventPublisherContext = stopChargingEventPublisherContext;
        this.meterValuesPresenterContext = meterValuesPresenterContext;
        this.chargeTransferExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void startChargeTransfer() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(chargeTransferBlockTask, chargeTransferExecutor);
        future.thenRun(() -> {
            StopChargingEventPublisher stopChargingEventPublisher = stopChargingEventPublisherContext.getStopChargingEventPublisherImpl(chargeTransferBlockTask.getShutdownInitiator());
            stopChargingEventPublisher.publishStopChargingEvent();
        });
    }

    @Override
    public void presentMeterValues(String requester) {
        MeterValuesPresenter meterValuesPresenter = meterValuesPresenterContext.getMeterValuesPresenterContext(requester);
        meterValuesPresenter.present(chargeTransferBlockTask.getMeterValues());
    }

    @Override
    public void setCharge(Charge charge) {
        chargeTransferBlockTask.setCharge(charge);
    }

    @Override
    public void stopChargeTransfer(String shutdownInitiator) {
        chargeTransferBlockTask.stop(shutdownInitiator);
    }
}
