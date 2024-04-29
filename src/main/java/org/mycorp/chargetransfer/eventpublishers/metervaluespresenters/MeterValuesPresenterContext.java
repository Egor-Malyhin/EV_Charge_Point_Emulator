package org.mycorp.chargetransfer.eventpublishers.metervaluespresenters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MeterValuesPresenterContext {
    private final Map<String, MeterValuesPresenter> meterValuesPresenterMap;

    @Autowired
    public MeterValuesPresenterContext(Map<String, MeterValuesPresenter> meterValuesPresenterMap) {
        this.meterValuesPresenterMap = meterValuesPresenterMap;
    }

    public MeterValuesPresenter getMeterValuesPresenterImpl(String requester){
        return meterValuesPresenterMap.get(requester);
    }
}
