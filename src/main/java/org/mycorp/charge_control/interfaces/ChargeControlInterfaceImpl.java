package org.mycorp.charge_control.interfaces;

import org.mycorp.charge_control.ChargeControlSystem;
import org.mycorp.models.StationState;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ChargeControlInterfaceImpl {
    protected final StationState stationState;

    @Autowired
    public ChargeControlInterfaceImpl(ChargeControlSystem chargeControlSystem) {
        this.stationState = chargeControlSystem.getStationState();
    }
}
