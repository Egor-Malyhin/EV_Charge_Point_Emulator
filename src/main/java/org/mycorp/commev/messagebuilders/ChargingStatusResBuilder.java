package org.mycorp.commev.messagebuilders;

import org.mycorp.models.MeterValues;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.messages.v2g.res.ChargingStatusRes;
import org.mycorp.models.messages.v2g.types.EVSENotification;
import org.mycorp.models.messages.v2g.types.MeterInfo;
import org.mycorp.models.messages.v2g.types.ResponseCode;

import static org.mycorp.models.messages.v2g.types.ResponseCode.OK;

public class ChargingStatusResBuilder extends PowerDeliveryResBuilder {
    private final MeterValues meterValues;

    public ChargingStatusResBuilder(ResponseCode responseCode, EVSENotification evseNotification, MeterValues meterValues) {
        super(responseCode, evseNotification);
        this.meterValues = meterValues;
    }

    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new ChargingStatusRes(OK, super.createAC_EVSEStatus(), StationCharacteristics.evseId, createMeterInfo());
        return this;
    }

    private MeterInfo createMeterInfo() {
        return new MeterInfo(StationCharacteristics.meterId, (long) meterValues.getSampledValue().get(0).getValue());
    }

}
