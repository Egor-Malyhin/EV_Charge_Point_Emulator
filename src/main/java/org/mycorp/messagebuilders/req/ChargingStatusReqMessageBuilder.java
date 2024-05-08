package org.mycorp.messagebuilders.req;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messagebuilders.MessageBuilderImpl;
import org.mycorp.messages.req.ChargingStatusReq;

public class ChargingStatusReqMessageBuilder extends MessageBuilderImpl {
    public ChargingStatusReqMessageBuilder(byte[] sessionId) {
        super(sessionId);
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new ChargingStatusReq();
        return this;
    }
}
