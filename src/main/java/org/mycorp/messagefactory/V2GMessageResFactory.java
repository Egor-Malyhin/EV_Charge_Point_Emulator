package org.mycorp.messagefactory;

import org.mycorp.messagebuilders.MessageBuilder;
import org.mycorp.messagebuilders.MessageBuildersDirector;
import org.mycorp.messagebuilders.res.*;
import org.mycorp.messages.V2GMessage;
import org.mycorp.messages.types.enums.EVSENotification;
import org.mycorp.messages.types.enums.ResponseCode;

//Factory for V2G res messages
public class V2GMessageResFactory {
    public static V2GMessage createSessionSetupResMessage(byte[] sessionId, ResponseCode responseCode, String evseId, long stationTimeStamp) {
        return runDirector(new SessionSetupResBuilder(sessionId, responseCode, evseId, stationTimeStamp));
    }

    public static V2GMessage createSessionStopResMessage(byte[] sessionId, ResponseCode responseCode) {
        return runDirector(new SessionStopResBuilder(sessionId, responseCode));
    }

    public static V2GMessage createChargeParameterDiscoveryResMessage(byte[] sessionId, ResponseCode responseCode, int ratedVoltageValue, int maxCurrentValue) {
        return runDirector(new ChargeParameterDiscoveryResBuilder(sessionId, responseCode, ratedVoltageValue, maxCurrentValue));
    }

    public static V2GMessage createChargingStatusRes(byte[] sessionId, ResponseCode responseCode, EVSENotification evseNotification, String evseId, long meterValues, int meterId) {
        return runDirector(new ChargingStatusResBuilder(sessionId, responseCode, evseNotification, evseId, meterValues, meterId));
    }

    public static V2GMessage createPowerDeliveryRes(byte[] sessionId, ResponseCode responseCode, EVSENotification evseNotification) {
        return runDirector(new PowerDeliveryResBuilder(sessionId, responseCode, evseNotification));
    }

    private static V2GMessage runDirector(MessageBuilder messageBuilder) {
        MessageBuildersDirector director = new MessageBuildersDirector(messageBuilder);
        return director.create();
    }
}
