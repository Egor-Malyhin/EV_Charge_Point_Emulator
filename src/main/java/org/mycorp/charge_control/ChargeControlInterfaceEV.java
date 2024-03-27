package org.mycorp.charge_control;

public interface ChargeControlInterfaceEV {
    void startAuthorize(byte[] idTag);
    void startChargingRequest();
    void stopChargingRequest();
    void getChargingStatus();
    void prepareCharging(float eAmount);
    void closeSessionRequest();
}
