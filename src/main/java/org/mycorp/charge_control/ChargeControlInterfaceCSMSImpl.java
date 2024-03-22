package org.mycorp.charge_control;

import org.springframework.beans.factory.annotation.Autowired;

public class ChargeControlInterfaceCSMSImpl implements ChargeControlInterfaceCSMS{
    private ChargeControlSystem system;

    @Autowired
    public ChargeControlInterfaceCSMSImpl(ChargeControlSystem system){
        this.system = system;
    }

    @Override
    public void preparedCharging() {
        system.setStateCharacterisation(StationStateEnum.PREPARED);
    }

    @Override
    public void startCharging() {
        system.setStateCharacterisation(StationStateEnum.CHARGING);
    }

    @Override
    public void getAvailable() {
        system.setStateCharacterisation(StationStateEnum.AVAILABLE);
    }
}
