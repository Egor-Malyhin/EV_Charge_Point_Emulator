package org.mycorp.v2g;

import org.mycorp.v2g.res.*;

public enum V2GMessagesClassification {
    CHARGE_PARAMETER_DISCOVERY_RES(ChargeParameterDiscoveryRes.class),
    SESSION_SETUP_RES(SessionSetupRes.class),
    POWER_DELIVERY_RES(PowerDeliveryRes.class),
    CHARGING_STATUS_RES(ChargingStatusRes.class),
    SESSION_STOP_RES(SessionStopRes.class);

    private final Class<? extends V2GBodyAbstractType> messageType;

    V2GMessagesClassification(Class<? extends V2GBodyAbstractType> messageType) {
        this.messageType = messageType;
    }

    public Class<? extends V2GBodyAbstractType> getMessageType() {
        return messageType;
    }
}
