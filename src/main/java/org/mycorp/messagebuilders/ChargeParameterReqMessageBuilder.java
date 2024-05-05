package org.mycorp.messagebuilders;

import org.mycorp.v2g.req.ChargeParameterDiscoveryReq;
import org.mycorp.v2g.types.AC_EVChargeParameter;
import org.mycorp.v2g.types.PhysicalValue;
import org.mycorp.v2g.types.RequestedEnergyTransferMode;

public class ChargeParameterReqMessageBuilder extends MessageBuilderImpl {
    private final RequestedEnergyTransferMode requestedEnergyTransferMode;
    private final PhysicalValue eAmount;
    private final PhysicalValue evMaxVoltage;
    private final PhysicalValue evMaxCurrent;
    private final PhysicalValue evMinCurrent;

    public ChargeParameterReqMessageBuilder(RequestedEnergyTransferMode requestedEnergyTransferMode, PhysicalValue eAmount, PhysicalValue evMaxVoltage, PhysicalValue evMaxCurrent, PhysicalValue evMinCurrent) {
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

    private AC_EVChargeParameter createEAmount(){
        return new AC_EVChargeParameter(eAmount, evMaxVoltage, evMaxCurrent, evMinCurrent);
    }
}
//EVDataVariables.getInstance().getEAmount(), EVCharacteristics.evMaxVoltage, EVCharacteristics.evMaxCurrent, EVCharacteristics.evMinCurrent