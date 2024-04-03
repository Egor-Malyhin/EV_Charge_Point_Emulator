package org.mycorp.ev_communication.message_builders;

import org.mycorp.models.v2g_messages.res.PowerDeliveryRes;
import org.mycorp.models.v2g_messages.types.AC_EVSEStatus;
import org.mycorp.models.v2g_messages.types.EVSENotification;
import org.mycorp.models.v2g_messages.types.ResponseCode;

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
