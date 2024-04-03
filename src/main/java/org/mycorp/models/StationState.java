package org.mycorp.models;

public class StationState {
    private StationStateEnum state;
    private Charge preparedCharge;
    private EVCharacterisation evCharacteristic;
    private boolean isChargingOn;
    private boolean isStateChanged;
    private final Object lock;

    public StationState(StationStateEnum state, Charge preparedCharge, EVCharacterisation evCharacteristic, boolean isChargingOn) {
        this.state = state;
        this.preparedCharge = preparedCharge;
        this.evCharacteristic = evCharacteristic;
        this.isChargingOn = isChargingOn;
        this.lock = new Object();
    }

    public StationStateEnum getState() {
        synchronized (lock) {
            while ((!isStateChanged) && (state!=StationStateEnum.AVAILABLE)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            isStateChanged = false;
            return state;
        }
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
        synchronized (lock) {
            this.state = state;
            isStateChanged = true;
            lock.notifyAll();
        }
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
