package org.mycorp.charge_control;

import org.mycorp.models.StationState;
import org.mycorp.models.StationStateEnum;
import org.springframework.beans.factory.annotation.Autowired;

public class ChargeControlInterfaceCSMSImpl implements ChargeControlInterfaceCSMS{
    private final StationState stationState;

    @Autowired
    public ChargeControlInterfaceCSMSImpl(ChargeControlSystem system){
        this.stationState = system.getStationState();
    }

    @Override
    public void preparedCharging() {
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
}
