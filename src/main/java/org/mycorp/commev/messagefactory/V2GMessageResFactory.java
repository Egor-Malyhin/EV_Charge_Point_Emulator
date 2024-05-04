package org.mycorp.commev.messagefactory;

import org.mycorp.commev.messagebuilders.*;
import org.mycorp.models.MeterValues;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.mycorp.models.messages.v2g.types.EVSENotification;
import org.mycorp.models.messages.v2g.types.ResponseCode;

//Factory for V2G res messages
public class V2GMessageResFactory {
    public static V2GMessage createSessionSetupResMessage(ResponseCode responseCode) {
        return runDirector(new SessionSetupResBuilder(responseCode));
    }

    public static V2GMessage createSessionStopResMessage(ResponseCode responseCode) {
        return runDirector(new SessionStopResBuilder(responseCode));
    }

    public static V2GMessage createChargeParameterDiscoveryResMessage(ResponseCode responseCode, int ratedVoltageValue, int maxCurrentValue) {
        return runDirector(new ChargeParameterDiscoveryResBuilder(responseCode, ratedVoltageValue, maxCurrentValue));
    }

    public static V2GMessage createChargingStatusRes(ResponseCode responseCode, EVSENotification evseNotification, MeterValues meterValues) {
        return runDirector(new ChargingStatusResBuilder(responseCode, evseNotification, meterValues));
    }

    public static V2GMessage createPowerDeliveryRes(ResponseCode responseCode, EVSENotification evseNotification) {
        return runDirector(new PowerDeliveryResBuilder(responseCode, evseNotification));
    }

    private static V2GMessage runDirector(MessageBuilder messageBuilder) {
        MessageBuildersDirector director = new MessageBuildersDirector(messageBuilder);
        return director.create();
    }
}
