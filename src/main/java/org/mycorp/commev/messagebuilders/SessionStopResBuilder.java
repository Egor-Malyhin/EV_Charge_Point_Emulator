package org.mycorp.commev.messagebuilders;

import org.mycorp.models.messages.v2g.res.SessionStopRes;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;

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
