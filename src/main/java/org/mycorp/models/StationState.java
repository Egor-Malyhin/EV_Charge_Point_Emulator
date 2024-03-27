package org.mycorp.models;

public class StationState {
    StationStateEnum state;
    Charge preparedCharge;
    EVCharacterisation evCharacteristic;
    boolean isChargingOn;

    public StationState(StationStateEnum state, Charge preparedCharge, EVCharacterisation evCharacteristic, boolean isChargingOn) {
        this.state = state;
        this.preparedCharge = preparedCharge;
        this.evCharacteristic = evCharacteristic;
        this.isChargingOn = isChargingOn;
    }

    public StationStateEnum getState() {
        return state;
    }

    public Charge getPreparedCharge() {
        return preparedCharge;
    }

    public EVCharacterisation getEvCharacteristic() {
        return evCharacteristic;
    }

    public boolean isChargingOn() {
        return isChargingOn;
    }

    public void setState(StationStateEnum state) {
        this.state = state;
    }

    public void setPreparedCharge(Charge preparedCharge) {
        this.preparedCharge = preparedCharge;
    }

    public void setEvCharacteristic(EVCharacterisation evCharacteristic) {
        this.evCharacteristic = evCharacteristic;
    }

    public void setChargingOn(boolean chargingOn) {
        isChargingOn = chargingOn;
    }
}
