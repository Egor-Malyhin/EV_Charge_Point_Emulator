package org.mycorp.models;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class MeterValues implements Cloneable {
    private Instant timestamp;
    private List<SampledValue> sampledValue;

    public MeterValues(Instant timestamp, List<SampledValue> sampledValue) {
        this.timestamp = timestamp;
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
