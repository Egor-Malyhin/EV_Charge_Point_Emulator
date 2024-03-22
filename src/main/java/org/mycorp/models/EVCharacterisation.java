package org.mycorp.models;

public class EVCharacterisation {
    private String idTag;
    private float EАmount;
    private float EVMaxVoltage;
    private float EVMaxCurrent;
    private float EVMinCurrent;

    public EVCharacterisation(String idTag, float EАmount, float EVMaxVoltage, float EVMaxCurrent, float EVMinCurrent) {
        this.idTag = idTag;
        this.EАmount = EАmount;
        this.EVMaxVoltage = EVMaxVoltage;
        this.EVMaxCurrent = EVMaxCurrent;
        this.EVMinCurrent = EVMinCurrent;
    }

    public String getIdTag() {
        return idTag;
    }

    public float getEАmount() {
        return EАmount;
    }

    public float getEVMaxVoltage() {
        return EVMaxVoltage;
    }

    public float getEVMaxCurrent() {
        return EVMaxCurrent;
    }

    public float getEVMinCurrent() {
        return EVMinCurrent;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }

    public void setEАmount(float EАmount) {
        this.EАmount = EАmount;
    }

    public void setEVMaxVoltage(float EVMaxVoltage) {
        this.EVMaxVoltage = EVMaxVoltage;
    }

    public void setEVMaxCurrent(float EVMaxCurrent) {
        this.EVMaxCurrent = EVMaxCurrent;
    }

    public void setEVMinCurrent(float EVMinCurrent) {
        this.EVMinCurrent = EVMinCurrent;
    }
}
