package org.mycorp.models;

public class SampledValue {
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
}
