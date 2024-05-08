package org.mycorp.messagebuilders.res;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messages.res.SessionSetupRes;
import org.mycorp.messages.types.enums.ResponseCode;

public class SessionSetupResBuilder extends MessageBuilderRes {
    private final String evseId;
    private final long stationTimeStamp;

    public SessionSetupResBuilder(byte[] sessionId, ResponseCode responseCode, String evseId, long stationTimeStamp) {
        super(sessionId, responseCode);
        this.evseId = evseId;
        this.stationTimeStamp = stationTimeStamp;
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionSetupRes(responseCode, evseId, stationTimeStamp);
        return this;
    }
}
