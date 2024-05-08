package org.mycorp.messagebuilders.res;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messages.res.ChargeParameterDiscoveryRes;
import org.mycorp.messages.types.AC_EVSEChargeParameter;
import org.mycorp.messages.types.AC_EVSEStatus;
import org.mycorp.messages.types.PhysicalValue;
import org.mycorp.messages.types.enums.EVSENotification;
import org.mycorp.messages.types.enums.ResponseCode;

import static org.mycorp.messages.types.enums.EVSEProcessing.FINISHED;

public class ChargeParameterDiscoveryResBuilder extends MessageBuilderRes {
    private final int ratedVoltageValue;
    private final int maxCurrentValue;

    public ChargeParameterDiscoveryResBuilder(byte[] sessionId, ResponseCode responseCode, int ratedVoltageValue, int maxCurrentValue) {
        super(sessionId, responseCode);
        this.ratedVoltageValue = ratedVoltageValue;
        this.maxCurrentValue = maxCurrentValue;
    }

    @Override
    public MessageBuilder createBodyType() {
        this.v2GBodyAbstractType = new ChargeParameterDiscoveryRes(responseCode, createChargeParameter(), FINISHED);
        return this;
    }

    private AC_EVSEStatus createAC_EVSEStatus() {
        return new AC_EVSEStatus(true, 5, EVSENotification.NONE);
    }

    private PhysicalValue createEvseNominalVoltage() {
        return new PhysicalValue(1, "V", ratedVoltageValue);
    }

    private PhysicalValue createEvseMaxCurrent() {
        return new PhysicalValue(1, "A", maxCurrentValue);
    }

    private AC_EVSEChargeParameter createChargeParameter() {
        return new AC_EVSEChargeParameter(createAC_EVSEStatus(), createEvseNominalVoltage(), createEvseMaxCurrent());
    }
}
