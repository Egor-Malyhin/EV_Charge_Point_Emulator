package org.mycorp.commev.messagebuilders;

import org.mycorp.models.messages.v2g.res.ChargeParameterDiscoveryRes;
import org.mycorp.models.messages.v2g.types.*;
import org.mycorp.models.messages.v2g.types.enums.EVSENotification;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;

import static org.mycorp.models.messages.v2g.types.enums.EVSEProcessing.FINISHED;

public class ChargeParameterDiscoveryResBuilder extends MessageBuilderImpl {
    private final int ratedVoltageValue;
    private final int maxCurrentValue;

    public ChargeParameterDiscoveryResBuilder(ResponseCode responseCode, int ratedVoltageValue, int maxCurrentValue) {
        super(responseCode);
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
