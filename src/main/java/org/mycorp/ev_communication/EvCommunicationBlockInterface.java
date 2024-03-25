package org.mycorp.ev_communication;

import org.mycorp.models.MeterValues;

public interface EvCommunicationBlockInterface {
    void sendPowerRes(boolean canStartCharging);
    void sendChargeParameterRes();
    void sendSessionStopRes();
    void sendSessionSetupRes(boolean condition);
    void sendChargingStatusRes(boolean isChargingOn, MeterValues meterValues);
}
