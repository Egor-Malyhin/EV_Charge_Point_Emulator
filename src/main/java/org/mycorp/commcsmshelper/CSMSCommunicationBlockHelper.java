package org.mycorp.commcsmshelper;

import org.mycorp.models.events.commcsmshelper.SendBootNotification;
import org.mycorp.models.events.common.GetMeterValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class CSMSCommunicationBlockHelper implements CSMSCommunicationBlockHelperInterface {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ScheduledExecutorService meterValuesRequesterExecutor;
    private ScheduledFuture<?> scheduledFuture;

    @Autowired
    public CSMSCommunicationBlockHelper(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.meterValuesRequesterExecutor = Executors.newSingleThreadScheduledExecutor();
    }


    @Override
    public void sendBootNotification() {
        applicationEventPublisher.publishEvent(new SendBootNotification());
    }

    @Override
    public void startMeterValuesRequester() {
        scheduledFuture = meterValuesRequesterExecutor.scheduleWithFixedDelay(() -> {
            applicationEventPublisher.publishEvent(new GetMeterValues(CSMSCommunicationBlockHelper.class));
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
