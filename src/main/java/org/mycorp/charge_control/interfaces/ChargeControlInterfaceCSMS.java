package org.mycorp.charge_control.interfaces;

public interface ChargeControlInterfaceCSMS {
    void authorized();

    void startCharging();

    void getAvailable();

    void unauthorized();
}
