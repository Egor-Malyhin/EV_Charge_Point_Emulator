package org.mycorp.ev_communication.message_builders;

import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.v2g_messages.res.ChargeParameterDiscoveryRes;
import org.mycorp.models.v2g_messages.types.*;

import static org.mycorp.models.v2g_messages.types.EVSEProcessing.FINISHED;

public class ChargeParameterDiscoveryResBuilder extends MessageBuilderImpl {

    public ChargeParameterDiscoveryResBuilder(ResponseCode responseCode) {
        super(responseCode);
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
        return new PhysicalValue(1, "V", StationCharacteristics.ratedVoltage);
    }

    private PhysicalValue createEvseMaxCurrent() {
        return new PhysicalValue(1, "A", StationCharacteristics.maxCurrent);
    }

    private AC_EVSEChargeParameter createChargeParameter() {
        return new AC_EVSEChargeParameter(createAC_EVSEStatus(), createEvseNominalVoltage(), createEvseMaxCurrent());
    }
}
