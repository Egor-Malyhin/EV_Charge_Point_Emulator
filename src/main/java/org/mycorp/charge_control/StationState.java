package org.mycorp.charge_control;

import org.mycorp.models.Charge;
import org.mycorp.models.EVCharacterisation;

public class StationState {
    StationStateEnum state;
    Charge preparedCharge;
    EVCharacterisation evCharacteristic;

    public StationState(StationStateEnum state, Charge preparedCharge, EVCharacterisation evCharacteristic) {
        this.state = state;
        this.preparedCharge = preparedCharge;
        this.evCharacteristic = evCharacteristic;
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

    public void setState(StationStateEnum state) {
        this.state = state;
    }

    public void setPreparedCharge(Charge preparedCharge) {
        this.preparedCharge = preparedCharge;
    }

    public void setEvCharacteristic(EVCharacterisation evCharacteristic) {
        this.evCharacteristic = evCharacteristic;
    }
}
