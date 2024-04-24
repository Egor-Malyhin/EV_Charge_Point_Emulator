package org.mycorp.chargetransfer.eventpublishers.metervaluespresenters;

import org.mycorp.models.MeterValues;
import org.mycorp.models.events.chargetransfer.MeterValuesToCSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CSMSMeterValuesPresenter extends MeterValuesPresenterImpl{
    @Autowired
    protected CSMSMeterValuesPresenter(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void present(MeterValues meterValues) {
        applicationEventPublisher.publishEvent(new MeterValuesToCSMS(this, meterValues));
    }
}
