package org.mycorp.models.v2g_messages;

import org.mycorp.models.v2g_messages.res.ChargeParameterDiscoveryRes;
import org.mycorp.models.v2g_messages.res.ChargingStatusRes;

public enum V2GMessagesClassification {
    CHARGE_PARAMETER_DISCOVERY_RES(ChargeParameterDiscoveryRes.class),
    CHARGING_STATUS_RES(ChargingStatusRes.class),
    CHARGE_PARAMETER_DISCOVERY_REQ(ChargeParameterDiscoveryReq.class),
    SESSION_SETUP_REQ(),
    POWER_DELIVERY_REQ(),
    CHARGING_STATUS_REQ(),
    SESSION_STOP_RES();

    private final Class<? extends V2GBodyAbstractType> messageType;

    V2GMessagesClassification(Class<? extends V2GBodyAbstractType> messageType){
        this.messageType=messageType;
    }

    public Class<? extends V2GBodyAbstractType> getMessageType(){
        return messageType;
    }
}
