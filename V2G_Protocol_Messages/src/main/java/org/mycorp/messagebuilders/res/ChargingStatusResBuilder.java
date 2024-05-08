package org.mycorp.messagebuilders.res;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messages.res.ChargingStatusRes;
import org.mycorp.messages.types.MeterInfo;
import org.mycorp.messages.types.enums.EVSENotification;
import org.mycorp.messages.types.enums.ResponseCode;

import static org.mycorp.messages.types.enums.ResponseCode.OK;

public class ChargingStatusResBuilder extends PowerDeliveryResBuilder {
    private final String evseId;
    private final long meterValues;
    private final int meterId;

    public ChargingStatusResBuilder(byte[] sessionId, ResponseCode responseCode, EVSENotification evseNotification, String evseId, long meterValues, int meterId) {
        super(sessionId, responseCode, evseNotification);
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
