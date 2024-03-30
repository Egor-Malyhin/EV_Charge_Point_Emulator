package org.mycorp.mediators.receivers;

import org.mycorp.ev_communication.EvCommunicationBlockInterface;
import org.mycorp.models.station_messages.ev_messages.SendChargingStatusResMessage;
import org.mycorp.models.station_messages.ev_messages.SendPowerResMessage;
import org.mycorp.models.station_messages.ev_messages.SendSessionSetupRes;
import org.mycorp.models.station_messages.StationMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class EvReceiver implements Receiver {
    private final EvCommunicationBlockInterface evCommunicationBlockInterface;

    @Autowired
    public EvReceiver(EvCommunicationBlockInterface evCommunicationBlockInterface) {
        this.evCommunicationBlockInterface = evCommunicationBlockInterface;
    }

    @Override
    public void receiveMessage(StationMessage message) {
        switch (message.getDescription()){
            case SEND_POWER_RES:
                evCommunicationBlockInterface.sendPowerRes(((SendPowerResMessage) message).isCanCharging());
                break;
            case SEND_CHARGE_PARAMETER_RES:
                evCommunicationBlockInterface.sendChargeParameterRes();
                break;
            case SEND_SESSION_STOP_RES:
                evCommunicationBlockInterface.sendSessionStopRes();
                break;
            case SEND_SESSION_SETUP_RES:
                evCommunicationBlockInterface.sendSessionSetupRes(((SendSessionSetupRes) message).isCondition());
                break;
            case SEND_CHARGING_STATUS_RES:
                evCommunicationBlockInterface.sendChargingStatusRes(((SendChargingStatusResMessage) message).isChargingOn(), ((SendChargingStatusResMessage) message).getMeterValues());
        }

    }
}
