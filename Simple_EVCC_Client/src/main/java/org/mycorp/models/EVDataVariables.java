package org.mycorp.models;

import lombok.Getter;
import lombok.Setter;
import org.mycorp.messages.types.PhysicalValue;
import org.mycorp.messages.types.enums.RequestedEnergyTransferMode;

@Setter
@Getter
public class EVDataVariables {
    private PhysicalValue eAmount;
    private RequestedEnergyTransferMode requestedEnergyTransferMode;

    private EVDataVariables() {
        eAmount = new PhysicalValue(1, "kWh", 100);
        requestedEnergyTransferMode = RequestedEnergyTransferMode.AC_single_phase_core;
    }

    public static EVDataVariables getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        static EVDataVariables instance = new EVDataVariables();
    }
}
