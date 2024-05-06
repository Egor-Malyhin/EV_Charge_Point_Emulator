package org.mycorp.commev.messagebuilders;

import org.mycorp.models.messages.v2g.res.PowerDeliveryRes;
import org.mycorp.models.messages.v2g.types.AC_EVSEStatus;
import org.mycorp.models.messages.v2g.types.enums.EVSENotification;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;

public class PowerDeliveryResBuilder extends MessageBuilderImpl {
    private final EVSENotification evseNotification;

    public PowerDeliveryResBuilder(ResponseCode responseCode, EVSENotification evseNotification) {
        super(responseCode);
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
