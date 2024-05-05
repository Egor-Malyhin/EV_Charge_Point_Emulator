package org.mycorp.messagebuilders;

import org.mycorp.models.EVCharacteristics;
import org.mycorp.v2g.req.SessionSetupReq;
import org.mycorp.v2g.types.ResponseCode;

public class SessionSetupReqMessageBuilder extends MessageBuilderImpl {
    private final byte[] evccId;

    public SessionSetupReqMessageBuilder(byte[] evccId) {
        this.evccId = evccId;
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionSetupReq(evccId);
        return this;
    }
}
