package org.mycorp.ev_communication;

import org.mycorp.models.MeterValues;
import org.mycorp.models.v2g_messages.V2GMessage;

public interface EVCommunicationBlockInterface {
    void sendMessage(V2GMessage v2gMessage);

    void sendPowerRes(boolean canStartCharging);

    void sendChargeParameterRes();

    void sendSessionStopRes();

    void sendSessionSetupRes(boolean condition);

    void sendChargingStatusRes(boolean isChargingOn, MeterValues meterValues);
}
