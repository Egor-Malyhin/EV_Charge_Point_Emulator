package org.mycorp.ev_communication.message_builders;

import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.v2g_messages.res.SessionSetupRes;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import java.time.Instant;

import static org.mycorp.models.v2g_messages.types.ResponseCode.OK;

public class SessionSetupResBuilder extends MessageBuilderImpl{

    public SessionSetupResBuilder(ResponseCode responseCode) {
        super(responseCode);
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new SessionSetupRes(OK, StationCharacteristics.evseId, Instant.now().toEpochMilli());
        return this;
    }
}
