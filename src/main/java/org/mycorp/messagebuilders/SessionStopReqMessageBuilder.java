package org.mycorp.messagebuilders;

import org.mycorp.v2g.req.SessionStopReq;
import org.mycorp.v2g.types.ChargingSession;

public class SessionStopReqMessageBuilder extends MessageBuilderImpl {
    private final ChargingSession chargingSession;

    public SessionStopReqMessageBuilder(ChargingSession chargingSession) {
        this.chargingSession = chargingSession;
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionStopReq(chargingSession);
        return this;
    }
}
