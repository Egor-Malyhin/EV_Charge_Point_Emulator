package org.mycorp.charge_control;

import org.springframework.beans.factory.annotation.Autowired;

public interface ChargeControlInterfaceCSMS {
    void preparedCharging();
    void startCharging();
    void getAvailable();
}
