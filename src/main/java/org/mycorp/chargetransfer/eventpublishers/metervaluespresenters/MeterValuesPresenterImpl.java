package org.mycorp.chargetransfer.eventpublishers.metervaluespresenters;

import org.springframework.context.ApplicationEventPublisher;

public abstract class MeterValuesPresenterImpl implements MeterValuesPresenter{
    protected final ApplicationEventPublisher applicationEventPublisher;

    protected MeterValuesPresenterImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
