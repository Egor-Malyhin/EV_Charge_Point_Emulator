package org.mycorp.models.station_messages;

import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.req.*;

public enum StationMessageClassification {
    SEND_POWER_RES,
    SEND_CHARGE_PARAMETER_RES,
    SEND_SESSION_STOP_RES,
    SEND_SESSION_SETUP_RES,
    SEND_CHARGING_STATUS_RES,
    SET_CHARGE,
    START_AUTHORIZE,
    START_CHARGING_REQUEST,
    STOP_CHARGING_REQUEST,
    GET_CHARGING_STATUS,
    PREPARE_CHARGING,
    CLOSE_SESSION_REQUEST,


}
