package org.mycorp.charge_control.interfaces;

import org.mycorp.charge_control.ChargeControlSystem;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mycorp.models.StationStateEnum.*;

public class ChargeControlInterfaceEVImpl extends ChargeControlInterfaceImpl implements ChargeControlInterfaceEV {
    @Autowired
    public ChargeControlInterfaceEVImpl(ChargeControlSystem chargeControlSystem) {
        super(chargeControlSystem);
    }

    @Override
    public void startAuthorize(byte[] idTag) {
        stationState.getEvCharacteristic().setIdTag(new String(idTag));
        stationState.setState(AUTHORISATION);
    }

    @Override
    public void startChargingRequest() {
        stationState.setState(PREPARED);
    }

    @Override
    public void stopChargingRequest() {
        stationState.setState(STOP_CHARGING);
    }

    @Override
    public void getChargingStatus() {
        stationState.setState(EV_CHARGING_STATUS_REQUEST);
    }

    @Override
    public void prepareCharging(float eAmount) {
        stationState.getEvCharacteristic().setEАmount(eAmount);
        stationState.setState(WAIT_CHARGING_REQUEST);
    }

    @Override
    public void closeSessionRequest() {
        stationState.setState(EV_END_SESSION);
    }
}
