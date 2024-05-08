package org.mycorp.chargetransfer.eventpublishers.metervaluespresenters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class MeterValuesPresenterContext {
    private final Map<String, MeterValuesPresenter> meterValuesPresenterMap;

    @Autowired
    public MeterValuesPresenterContext(@Qualifier("meterValuesPresenterMap") Map<String, MeterValuesPresenter> meterValuesPresenterMap) {
        this.meterValuesPresenterMap = meterValuesPresenterMap;
    }

    public Optional<MeterValuesPresenter> getMeterValuesPresenterImpl(String requester) {
        return Optional.ofNullable(meterValuesPresenterMap.get(requester));
    }
}
