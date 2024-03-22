package org.mycorp.models;

public class StationCharacteristics {
    private final float maxCurrent;
    private final float ratedCurrent;
    private final float ratedVoltage;
    private final float maxVoltage;
    private final float minVoltage;
    private final float ratedPower;

    private static StationCharacteristics instance;

    public StationCharacteristics(){
        this.maxCurrent=38.4f;
        this.ratedCurrent=32;
        this.ratedVoltage=230;
        this.maxVoltage=276;
        this.minVoltage=184;
        this.ratedPower=7;
    }

    public static StationCharacteristics getInstance(){
        if (instance==null)
            instance=new StationCharacteristics();
        return instance;
    }

    public float getMaxCurrent() {
        return maxCurrent;
    }

    public float getRatedCurrent() {
        return ratedCurrent;
    }

    public float getRatedVoltage() {
        return ratedVoltage;
    }

    public float getMaxVoltage() {
        return maxVoltage;
    }

    public float getMinVoltage() {
        return minVoltage;
    }

    public float getRatedPower() {
        return ratedPower;
    }
}
