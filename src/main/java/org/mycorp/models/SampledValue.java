package org.mycorp.models;

import java.util.Objects;

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

    public float getValue() {
        return value;
    }

    public String getMeasurand() {
        return measurand;
    }

    public String getLocation() {
        return location;
    }

    public String getUnit() {
        return unit;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setMeasurand(String measurand) {
        this.measurand = measurand;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUnit(String unit) {
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
