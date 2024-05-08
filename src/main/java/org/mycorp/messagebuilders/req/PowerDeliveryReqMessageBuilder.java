package org.mycorp.messagebuilders.req;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messagebuilders.MessageBuilderImpl;
import org.mycorp.messages.req.PowerDeliveryReq;
import org.mycorp.messages.types.enums.ChargeProgress;

public class PowerDeliveryReqMessageBuilder extends MessageBuilderImpl {
    private final ChargeProgress chargeProgress;

    public PowerDeliveryReqMessageBuilder(byte[] sessionId, ChargeProgress chargeProgress) {
        super(sessionId);
        this.chargeProgress = chargeProgress;
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new PowerDeliveryReq(chargeProgress);
        return this;
    }
}
