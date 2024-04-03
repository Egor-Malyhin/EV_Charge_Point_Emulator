package org.mycorp.charge_control.interfaces;

import org.mycorp.charge_control.ChargeControlSystem;
import org.mycorp.models.StationStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChargeControlInterfaceCSMSImpl extends ChargeControlInterfaceImpl implements ChargeControlInterfaceCSMS {
    @Autowired
    public ChargeControlInterfaceCSMSImpl(ChargeControlSystem chargeControlSystem) {
        super(chargeControlSystem);
    }

    @Override
    public void authorized() {
        stationState.setState(StationStateEnum.AUTHORIZED);
    }

    @Override
    public void startCharging() {
        stationState.setState(StationStateEnum.START_CHARGING);
    }

    @Override
    public void getAvailable() {
        stationState.setState(StationStateEnum.AVAILABLE);
    }

    @Override
    public void unauthorized() {
        stationState.setState(StationStateEnum.UNAUTHORIZED);
    }
}
