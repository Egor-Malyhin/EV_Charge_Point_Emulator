package org.mycorp.messagefactory;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messagebuilders.MessageBuildersDirector;
import org.mycorp.messagebuilders.req.*;
import org.mycorp.messages.V2GMessage;
import org.mycorp.messages.types.PhysicalValue;
import org.mycorp.messages.types.enums.ChargeProgress;
import org.mycorp.messages.types.enums.ChargingSession;
import org.mycorp.messages.types.enums.RequestedEnergyTransferMode;

public class V2GMessageReqFactory {
    public static V2GMessage createSessionSetupReqMessage(byte[] sessionId, byte[] evccId) {
        return runDirector(new SessionSetupReqMessageBuilder(sessionId, evccId));
    }

    public static V2GMessage createSessionStopReqMessage(byte[] sessionId, ChargingSession chargingSession) {
        return runDirector(new SessionStopReqMessageBuilder(sessionId, chargingSession));
    }

    public static V2GMessage createChargeParameterDiscoveryReqMessage(byte[] sessionId, RequestedEnergyTransferMode requestedEnergyTransferMode, PhysicalValue eAmount, PhysicalValue evMaxVoltage, PhysicalValue evMaxCurrent, PhysicalValue evMinCurrent) {
        return runDirector(new ChargeParameterReqMessageBuilder(sessionId, requestedEnergyTransferMode, eAmount, evMaxVoltage, evMaxCurrent, evMinCurrent));
    }

    public static V2GMessage createChargingStatusReq(byte[] sessionId) {
        return runDirector(new ChargingStatusReqMessageBuilder(sessionId));
    }

    public static V2GMessage createPowerDeliveryReq(byte[] sessionId, ChargeProgress chargeProgress) {
        return runDirector(new PowerDeliveryReqMessageBuilder(sessionId, chargeProgress));
    }

    private static V2GMessage runDirector(MessageBuilder messageBuilder) {
        MessageBuildersDirector director = new MessageBuildersDirector(messageBuilder);
        return director.create();
    }
}
