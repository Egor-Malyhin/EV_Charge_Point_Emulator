package org.mycorp.commev.messagebuilders;

import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.messages.v2g.res.SessionSetupRes;
import org.mycorp.models.messages.v2g.types.ResponseCode;

import java.time.Instant;

public class SessionSetupResBuilder extends MessageBuilderImpl {

    public SessionSetupResBuilder(ResponseCode responseCode) {
        super(responseCode);
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionSetupRes(responseCode, StationCharacteristics.evseId, Instant.now().toEpochMilli());
        return this;
    }
}
