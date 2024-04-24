package org.mycorp.chargetransfer.eventpublishers.metervaluespresenters;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class MeterValuesPresenterContext {
    private final Map<String, MeterValuesPresenter> meterValuesPresenterMap;

    @Autowired
    public MeterValuesPresenterContext(Map<String, MeterValuesPresenter> meterValuesPresenterMap) {
        this.meterValuesPresenterMap = meterValuesPresenterMap;
    }

    public MeterValuesPresenter getMeterValuesPresenterContext(String requester){
        return meterValuesPresenterMap.get(requester);
    }
}
