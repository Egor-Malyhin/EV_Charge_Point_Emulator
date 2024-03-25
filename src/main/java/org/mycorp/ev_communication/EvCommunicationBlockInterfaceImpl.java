package org.mycorp.ev_communication;

import org.mycorp.ev_communication.message_builders.*;
import org.mycorp.models.MeterValues;
import org.mycorp.models.v2g_messages.V2GMessage;
import org.mycorp.models.v2g_messages.types.EVSENotification;
import org.mycorp.models.v2g_messages.types.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mycorp.models.v2g_messages.types.EVSENotification.*;
import static org.mycorp.models.v2g_messages.types.ResponseCode.*;

public class EvCommunicationBlockInterfaceImpl implements EvCommunicationBlockInterface{

    private final EVCommunicationBlockHandler evCommunicationBlockHandler;
    @Autowired
    public EvCommunicationBlockInterfaceImpl(EVCommunicationBlockHandler evCommunicationBlockHandler){
        this.evCommunicationBlockHandler = evCommunicationBlockHandler;
    }
    @Override
    public void sendPowerRes(boolean canStartCharging) {
        ResponseCode responseCode = canStartCharging ? OK : FAILED_PowerDelivery_NotApplied;
        sendMessage(buildMessage(new PowerDeliveryResBuilder(OK, NONE)));
    }

    @Override
    public void sendChargeParameterRes() {
        sendMessage(buildMessage(new ChargeParameterDiscoveryResBuilder(OK)));
    }
    @Override
    public void sendSessionStopRes() {
        sendMessage(buildMessage(new SessionStopResBuilder(OK)));
    }

    @Override
    public void sendSessionSetupRes(boolean condition) {
        ResponseCode responseCode = condition ? OK_NewSessionEstablished : FAILED;
        sendMessage(buildMessage(new SessionSetupResBuilder(responseCode)));
    }

    @Override
    public void sendChargingStatusRes(boolean isChargingOn, MeterValues meterValues) {
        EVSENotification evseNotification = isChargingOn ? NONE : STOPCHARGING;
        sendMessage(buildMessage(new ChargingStatusResBuilder(OK, evseNotification, meterValues)));
    }

    private V2GMessage buildMessage(MessageBuilder builder){
        MessageBuildersDirector director = new MessageBuildersDirector(builder);
        return director.create();
    }

    private void sendMessage(V2GMessage v2gMessage){
        evCommunicationBlockHandler.sendMessage(v2gMessage);
    }
}
