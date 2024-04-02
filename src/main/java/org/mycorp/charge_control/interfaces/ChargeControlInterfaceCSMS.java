package org.mycorp.charge_control.interfaces;

import org.springframework.beans.factory.annotation.Autowired;

public interface ChargeControlInterfaceCSMS {
    void authorized();

    void startCharging();

    void getAvailable();

    void unauthorized();
}
