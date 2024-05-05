package org.mycorp.messagefactory;

import org.mycorp.messagebuilders.*;
import org.mycorp.v2g.V2GMessage;
import org.mycorp.v2g.types.ChargeProgress;
import org.mycorp.v2g.types.ChargingSession;
import org.mycorp.v2g.types.PhysicalValue;
import org.mycorp.v2g.types.RequestedEnergyTransferMode;

public class V2GMessageReqFactory {
    public static V2GMessage createSessionSetupReqMessage(byte[] evccId) {
        return runDirector(new SessionSetupReqMessageBuilder(evccId));
    }

    public static V2GMessage createSessionStopReqMessage(ChargingSession chargingSession) {
        return runDirector(new SessionStopReqMessageBuilder(chargingSession));
    }

    public static V2GMessage createChargeParameterDiscoveryReqMessage(RequestedEnergyTransferMode requestedEnergyTransferMode, PhysicalValue eAmount, PhysicalValue evMaxVoltage, PhysicalValue evMaxCurrent, PhysicalValue evMinCurrent) {
        return runDirector(new ChargeParameterReqMessageBuilder(requestedEnergyTransferMode, eAmount, evMaxVoltage, evMaxCurrent, evMinCurrent));
    }

    public static V2GMessage createChargingStatusReq() {
        return runDirector(new ChargingStatusReqMessageBuilder());
    }

    public static V2GMessage createPowerDeliveryReq(ChargeProgress chargeProgress) {
        return runDirector(new PowerDeliveryReqMessageBuilder(chargeProgress));
    }

    private static V2GMessage runDirector(MessageBuilder messageBuilder) {
        MessageBuildersDirector director = new MessageBuildersDirector(messageBuilder);
        return director.create();
    }
}
