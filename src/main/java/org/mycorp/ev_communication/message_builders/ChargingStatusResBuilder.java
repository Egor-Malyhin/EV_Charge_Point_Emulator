package org.mycorp.ev_communication.message_builders;

import org.mycorp.models.MeterValues;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.v2g_messages.res.ChargingStatusRes;
import org.mycorp.models.v2g_messages.types.EVSENotification;
import org.mycorp.models.v2g_messages.types.MeterInfo;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import static org.mycorp.models.v2g_messages.types.ResponseCode.OK;

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
