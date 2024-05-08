package org.mycorp.messagebuilders.req;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messagebuilders.MessageBuilderImpl;
import org.mycorp.messages.req.SessionStopReq;
import org.mycorp.messages.types.enums.ChargingSession;

public class SessionStopReqMessageBuilder extends MessageBuilderImpl {
    private final ChargingSession chargingSession;

    public SessionStopReqMessageBuilder(byte[] sessionId, ChargingSession chargingSession) {
        super(sessionId);
        this.chargingSession = chargingSession;
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionStopReq(chargingSession);
        return this;
    }
}
