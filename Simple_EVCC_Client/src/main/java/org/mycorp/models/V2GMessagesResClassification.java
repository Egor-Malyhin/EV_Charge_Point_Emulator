package org.mycorp.models;

import lombok.Getter;
import org.mycorp.messages.V2GBodyAbstractType;
import org.mycorp.messages.res.*;

@Getter
public enum V2GMessagesResClassification {
    CHARGE_PARAMETER_DISCOVERY_RES(ChargeParameterDiscoveryRes.class),
    SESSION_SETUP_RES(SessionSetupRes.class),
    POWER_DELIVERY_RES(PowerDeliveryRes.class),
    CHARGING_STATUS_RES(ChargingStatusRes.class),
    SESSION_STOP_RES(SessionStopRes.class);

    private final Class<? extends V2GBodyAbstractType> messageType;

    V2GMessagesResClassification(Class<? extends V2GBodyAbstractType> messageType) {
        this.messageType = messageType;
    }

}
