package org.mycorp.ev_communication.message_builders;

import org.mycorp.models.v2g_messages.res.SessionStopRes;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import static org.mycorp.models.v2g_messages.types.ResponseCode.OK;

public class SessionStopResBuilder extends MessageBuilderImpl{
    public SessionStopResBuilder(ResponseCode responseCode) {
        super(responseCode);
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionStopRes(OK);
        return this;
    }
}
