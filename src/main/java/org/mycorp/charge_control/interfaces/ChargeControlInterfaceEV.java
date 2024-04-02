package org.mycorp.charge_control.interfaces;

public interface ChargeControlInterfaceEV {
    void startAuthorize(byte[] idTag);

    void startChargingRequest();

    void stopChargingRequest();

    void getChargingStatus();

    void prepareCharging(float eAmount);

    void closeSessionRequest();
}
