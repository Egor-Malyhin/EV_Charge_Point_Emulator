package org.mycorp.messagebuilders.req;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messagebuilders.MessageBuilderImpl;
import org.mycorp.messages.req.ChargeParameterDiscoveryReq;
import org.mycorp.messages.types.AC_EVChargeParameter;
import org.mycorp.messages.types.PhysicalValue;
import org.mycorp.messages.types.enums.RequestedEnergyTransferMode;

public class ChargeParameterReqMessageBuilder extends MessageBuilderImpl {
    private final RequestedEnergyTransferMode requestedEnergyTransferMode;
    private final PhysicalValue eAmount;
    private final PhysicalValue evMaxVoltage;
    private final PhysicalValue evMaxCurrent;
    private final PhysicalValue evMinCurrent;

    public ChargeParameterReqMessageBuilder(byte[] sessionId, RequestedEnergyTransferMode requestedEnergyTransferMode, PhysicalValue eAmount, PhysicalValue evMaxVoltage, PhysicalValue evMaxCurrent, PhysicalValue evMinCurrent) {
        super(sessionId);
        this.requestedEnergyTransferMode = requestedEnergyTransferMode;
        this.eAmount = eAmount;
        this.evMaxVoltage = evMaxVoltage;
        this.evMaxCurrent = evMaxCurrent;
        this.evMinCurrent = evMinCurrent;
    }


    @Override
    public MessageBuilder createBodyType() {
        v2GBodyAbstractType = new ChargeParameterDiscoveryReq(requestedEnergyTransferMode, createEAmount());
        return this;
    }

    private AC_EVChargeParameter createEAmount() {
        return new AC_EVChargeParameter(eAmount, evMaxVoltage, evMaxCurrent, evMinCurrent);
    }
}
//EVDataVariables.getInstance().getEAmount(), EVCharacteristics.evMaxVoltage, EVCharacteristics.evMaxCurrent, EVCharacteristics.evMinCurrent