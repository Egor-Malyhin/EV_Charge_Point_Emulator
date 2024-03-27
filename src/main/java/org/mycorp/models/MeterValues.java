package org.mycorp.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MeterValues implements Cloneable {
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

    @Override
    public MeterValues clone() {
        try {
            MeterValues clone = (MeterValues) super.clone();
            clone.sampledValue = new ArrayList<>();
            for (SampledValue obj : this.sampledValue) {
                clone.sampledValue.add(obj.clone());
            }
            clone.timestamp = Instant.ofEpochMilli(this.timestamp.toEpochMilli());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
