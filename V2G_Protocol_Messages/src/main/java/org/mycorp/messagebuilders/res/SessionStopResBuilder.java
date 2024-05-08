package org.mycorp.messagebuilders.res;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messages.res.SessionStopRes;
import org.mycorp.messages.types.enums.ResponseCode;

public class SessionStopResBuilder extends MessageBuilderRes {
    public SessionStopResBuilder(byte[] sessionId, ResponseCode responseCode) {
        super(sessionId, responseCode);
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionStopRes(responseCode);
        return this;
    }
}
