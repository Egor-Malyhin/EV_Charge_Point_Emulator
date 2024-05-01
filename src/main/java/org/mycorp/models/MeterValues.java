package org.mycorp.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public List<SampledValue> getSampledValue() {
        return sampledValue;
    }

    public void setSampledValue(List<SampledValue> sampledValue) {
        this.sampledValue = sampledValue;
    }

    @Override
    public String toString() {
        return "MeterValues{" +
                "timestamp=" + timestamp +
                ", sampledValue=" + sampledValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeterValues that)) return false;
        return Objects.equals(getTimestamp(), that.getTimestamp()) && Objects.equals(getSampledValue(), that.getSampledValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimestamp(), getSampledValue());
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
