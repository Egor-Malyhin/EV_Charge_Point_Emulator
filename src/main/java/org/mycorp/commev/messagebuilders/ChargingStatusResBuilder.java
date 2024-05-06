package org.mycorp.commev.messagebuilders;

import org.mycorp.models.messages.v2g.res.ChargingStatusRes;
import org.mycorp.models.messages.v2g.types.enums.EVSENotification;
import org.mycorp.models.messages.v2g.types.MeterInfo;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;

import static org.mycorp.models.messages.v2g.types.enums.ResponseCode.OK;

public class ChargingStatusResBuilder extends PowerDeliveryResBuilder {
    private final String evseId;
    private final long meterValues;
    private final int meterId;

    public ChargingStatusResBuilder(ResponseCode responseCode, EVSENotification evseNotification, String evseId, long meterValues, int meterId) {
        super(responseCode, evseNotification);
        this.evseId = evseId;
        this.meterValues = meterValues;
        this.meterId = meterId;
    }


    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new ChargingStatusRes(OK, super.createAC_EVSEStatus(), evseId, createMeterInfo());
        return this;
    }

    private MeterInfo createMeterInfo() {
        return new MeterInfo(meterId, meterValues);
    }

}
