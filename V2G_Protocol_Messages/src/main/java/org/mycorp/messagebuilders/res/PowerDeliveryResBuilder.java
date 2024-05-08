package org.mycorp.messagebuilders.res;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messages.res.PowerDeliveryRes;
import org.mycorp.messages.types.AC_EVSEStatus;
import org.mycorp.messages.types.enums.EVSENotification;
import org.mycorp.messages.types.enums.ResponseCode;

public class PowerDeliveryResBuilder extends MessageBuilderRes {
    private final EVSENotification evseNotification;

    public PowerDeliveryResBuilder(byte[] sessionId, ResponseCode responseCode, EVSENotification evseNotification) {
        super(sessionId, responseCode);
        this.evseNotification = evseNotification;
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new PowerDeliveryRes(responseCode, createAC_EVSEStatus());
        return this;
    }

    protected AC_EVSEStatus createAC_EVSEStatus() {
        return new AC_EVSEStatus(true, 5, evseNotification);
    }
}
