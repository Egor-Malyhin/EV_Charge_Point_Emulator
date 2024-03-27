package org.mycorp.models.v2g_messages;

import org.mycorp.models.v2g_messages.req.*;
import org.mycorp.models.v2g_messages.res.ChargeParameterDiscoveryRes;
import org.mycorp.models.v2g_messages.res.ChargingStatusRes;

public enum V2GMessagesClassification {
    CHARGE_PARAMETER_DISCOVERY_REQ(ChargeParameterDiscoveryReq.class),
    SESSION_SETUP_REQ(SessionSetupReq.class),
    POWER_DELIVERY_REQ(PowerDeliveryReq.class),
    CHARGING_STATUS_REQ(ChargingStatusReq.class),
    SESSION_STOP_REQ(SessionStopReq.class);

    private final Class<? extends V2GBodyAbstractType> messageType;

    V2GMessagesClassification(Class<? extends V2GBodyAbstractType> messageType){
        this.messageType=messageType;
    }

    public Class<? extends V2GBodyAbstractType> getMessageType(){
        return messageType;
    }
}
