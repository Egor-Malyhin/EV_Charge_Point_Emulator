package org.mycorp.models;

import java.time.Instant;
import java.util.List;

public class MeterValues {
    private Instant timestamp;
    private List<SampledValue> sampledValue;

    public MeterValues(Instant timestamp, List<SampledValue> sampledValue) {
        this.timestamp = timestamp;
        this.sampledValue = sampledValue;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public List<SampledValue> getSampledValue() {
        return sampledValue;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setSampledValue(List<SampledValue> sampledValue) {
        this.sampledValue = sampledValue;
    }
}
