package org.mycorp.charge_control.interfaces;

import org.mycorp.charge_control.ChargeControlSystem;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mycorp.models.StationStateEnum.STOP_CHARGING;

public class ChargeControlInterfaceChargeTransferImpl extends ChargeControlInterfaceImpl implements ChargeControlInterfaceChargeTransfer {
    @Autowired
    public ChargeControlInterfaceChargeTransferImpl(ChargeControlSystem chargeControlSystem) {
        super(chargeControlSystem);
    }

    @Override
    public void stopChargingByStation() {
        stationState.setState(STOP_CHARGING);
    }
}
