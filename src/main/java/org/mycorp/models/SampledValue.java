package org.mycorp.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class SampledValue implements Cloneable {
    private float value;
    private String measurand;
    private String location;
    private String unit;

    public SampledValue(float value, String measurand, String location, String unit) {
        this.value = value;
        this.measurand = measurand;
        this.location = location;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "SampledValue{" +
                "value=" + value +
                ", measurand='" + measurand + '\'' +
                ", location='" + location + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SampledValue that)) return false;
        return Float.compare(getValue(), that.getValue()) == 0 && Objects.equals(getMeasurand(), that.getMeasurand()) && Objects.equals(getLocation(), that.getLocation()) && Objects.equals(getUnit(), that.getUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getMeasurand(), getLocation(), getUnit());
    }

    @Override
    public SampledValue clone() {
        try {
            return (SampledValue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
