package org.mycorp.mediators.receivers;

import org.mycorp.charge_control.interfaces.ChargeControlInterfaceEV;
import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.control_system_messages_ev_comm.PrepareChargingMessage;
import org.mycorp.models.station_messages.control_system_messages_ev_comm.StartAuthorizeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChargeSystemReceiverEVComm implements Receiver {
    private final ChargeControlInterfaceEV chargeControlInterfaceEV;

    @Autowired
    public ChargeSystemReceiverEVComm(ChargeControlInterfaceEV chargeControlInterfaceEV) {
        this.chargeControlInterfaceEV = chargeControlInterfaceEV;
    }

    @Override
    public void receiveMessage(StationMessage message) {
        switch (message.getDescription()) {
            case START_AUTHORIZE:
                chargeControlInterfaceEV.startAuthorize(((StartAuthorizeMessage) message).getIdTag());
                break;
            case START_CHARGING_REQUEST:
                chargeControlInterfaceEV.startChargingRequest();
                break;
            case STOP_CHARGING_REQUEST:
                chargeControlInterfaceEV.stopChargingRequest();
                break;
            case GET_CHARGING_STATUS:
                chargeControlInterfaceEV.getChargingStatus();
                break;
            case PREPARE_CHARGING:
                chargeControlInterfaceEV.prepareCharging(((PrepareChargingMessage) message).getEAmount());
                break;
            case CLOSE_SESSION_REQUEST:
                chargeControlInterfaceEV.closeSessionRequest();
        }
    }
}
