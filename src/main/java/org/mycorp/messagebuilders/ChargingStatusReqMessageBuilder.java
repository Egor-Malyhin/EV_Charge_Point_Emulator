package org.mycorp.messagebuilders;

import org.mycorp.v2g.req.ChargingStatusReq;
import org.mycorp.v2g.res.ChargingStatusRes;

public class ChargingStatusReqMessageBuilder extends MessageBuilderImpl {
    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new ChargingStatusReq();
        return this;
    }
}
