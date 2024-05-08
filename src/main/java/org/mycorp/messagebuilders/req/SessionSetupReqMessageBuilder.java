package org.mycorp.messagebuilders.req;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messagebuilders.MessageBuilderImpl;
import org.mycorp.messages.req.SessionSetupReq;

public class SessionSetupReqMessageBuilder extends MessageBuilderImpl {
    private final byte[] evccId;

    public SessionSetupReqMessageBuilder(byte[] sessionId, byte[] evccId) {
        super(sessionId);
        this.evccId = evccId;
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionSetupReq(evccId);
        return this;
    }
}
