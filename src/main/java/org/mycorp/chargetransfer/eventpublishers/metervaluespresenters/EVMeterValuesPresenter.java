package org.mycorp.chargetransfer.eventpublishers.metervaluespresenters;

import org.mycorp.models.MeterValues;
import org.mycorp.models.events.chargetransfer.MeterValuesToEV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EVMeterValuesPresenter extends MeterValuesPresenterImpl {
    @Autowired
    protected EVMeterValuesPresenter(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void present(MeterValues meterValues, boolean isChargingOn) {
        applicationEventPublisher.publishEvent(new MeterValuesToEV(meterValues, isChargingOn));
    }
}
