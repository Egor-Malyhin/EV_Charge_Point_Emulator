package org.mycorp.commev.messagebuilders;

import org.mycorp.models.messages.v2g.res.SessionSetupRes;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;

public class SessionSetupResBuilder extends MessageBuilderImpl {
    private final String evseId;
    private final long stationTimeStamp;

    public SessionSetupResBuilder(ResponseCode responseCode, String evseId, long stationTimeStamp) {
        super(responseCode);
        this.evseId = evseId;
        this.stationTimeStamp = stationTimeStamp;
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionSetupRes(responseCode, evseId, stationTimeStamp);
        return this;
    }
}
