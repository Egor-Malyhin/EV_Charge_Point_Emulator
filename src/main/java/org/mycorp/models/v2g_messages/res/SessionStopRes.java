package org.mycorp.models.v2g_messages.res;

import org.mycorp.models.v2g_messages.types.ResponseCode;

public class SessionStopRes extends V2GMessageRes {
    public SessionStopRes(ResponseCode responseCode) {
        super(responseCode);
    }
}
