package org.mycorp.messagebuilders;

import org.mycorp.v2g.req.PowerDeliveryReq;
import org.mycorp.v2g.types.ChargeProgress;

public class PowerDeliveryReqMessageBuilder extends MessageBuilderImpl {
    private final ChargeProgress chargeProgress;

    public PowerDeliveryReqMessageBuilder(ChargeProgress chargeProgress){
        this.chargeProgress = chargeProgress;
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new PowerDeliveryReq(chargeProgress);
        return this;
    }
}
