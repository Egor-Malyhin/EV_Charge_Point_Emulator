package org.mycorp.commcsmshelper;

import org.mycorp.models.events.commcsmshelper.SendBootNotification;
import org.mycorp.models.events.commcsmshelper.TryConnecting;
import org.mycorp.models.events.common.GetMeterValues;
import org.mycorp.stationeventpublisher.StationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class CSMSCommunicationBlockHelper extends StationEventPublisher implements CSMSCommunicationBlockHelperInterface {
    private final ScheduledExecutorService meterValuesRequesterExecutor;
    private ScheduledFuture<?> scheduledFuture;

    @Autowired
    protected CSMSCommunicationBlockHelper(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        this.meterValuesRequesterExecutor = Executors.newSingleThreadScheduledExecutor();
    }


    @Override
    public void tryConnecting() {
        applicationEventPublisher.publishEvent(new TryConnecting(this));
    }

    @Override
    public void sendBootNotification() {
        applicationEventPublisher.publishEvent(new SendBootNotification(this));
    }

    @Override
    public void startMeterValuesRequester() {
        scheduledFuture = meterValuesRequesterExecutor.scheduleWithFixedDelay(() -> {
            applicationEventPublisher.publishEvent(new GetMeterValues(this, "CSMSCommunicationBlock"));
        }, 2, 2, TimeUnit.SECONDS);
    }

    @Override
    public void stopMeterValuesRequester() {
        scheduledFuture.cancel(false);
    }

    @PreDestroy
    private void clearMeterValuesRequesterExecutor() {
        meterValuesRequesterExecutor.shutdown();
    }
}
