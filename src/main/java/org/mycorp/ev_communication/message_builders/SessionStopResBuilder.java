package org.mycorp.ev_communication.message_builders;

import org.mycorp.models.v2g_messages.res.SessionStopRes;
import org.mycorp.models.v2g_messages.types.ResponseCode;

public class SessionStopResBuilder extends MessageBuilderImpl {
    public SessionStopResBuilder(ResponseCode responseCode) {
        super(responseCode);
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionStopRes(responseCode);
        return this;
    }
}
