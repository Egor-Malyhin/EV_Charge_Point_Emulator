package org.mycorp.charge_control;

import org.mycorp.mediators.senders.SenderChargeControlSystem;
import org.mycorp.models.StationState;
import org.mycorp.models.station_messages.charge_transfer_messages.StopChargingByStationMessage;
import org.mycorp.models.station_messages.control_system_messages_charge_transfer.SetChargeMessage;
import org.mycorp.models.station_messages.control_system_messages_csms_comm.SendAuthorizeMessage;
import org.mycorp.models.station_messages.control_system_messages_csms_comm.SendBootNotificationMessage;
import org.mycorp.models.station_messages.control_system_messages_csms_comm.SendStartTransactionMessage;
import org.mycorp.models.station_messages.ev_comm_messages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.mycorp.models.StationStateEnum.AVAILABLE;

@Component
public class ChargeControlSystem implements Runnable {
    private final SenderChargeControlSystem senderChargeControlSystem;
    private final InitSystem initSystem;
    private final StationState stateCharacterisation;

    @Autowired
    public ChargeControlSystem(InitSystem initSystem, @Qualifier("senderChargeControlSystem") SenderChargeControlSystem senderChargeControlSystem) {
        this.senderChargeControlSystem = senderChargeControlSystem;
        this.initSystem = initSystem;
        this.stateCharacterisation = new StationState(AVAILABLE, null, null, false);
    }

    @Override
    public void run() {
        initSystem.initialize();
        senderChargeControlSystem.sendMessage(new SendBootNotificationMessage());
        while (true) {
            System.out.println("Charging Station is Working.");
            try {
                Thread.sleep(100);
                switch (stateCharacterisation.getState()) {
                    case AUTHORISATION:
                        senderChargeControlSystem.sendMessage(new SendAuthorizeMessage(stateCharacterisation.getEvCharacteristic().getIdTag()));
                        break;
                    case AUTHORIZED:
                        senderChargeControlSystem.sendMessage(new SendSessionSetupRes(true));
                        break;
                    case WAIT_CHARGING_REQUEST:
                        senderChargeControlSystem.sendMessage(new SendChargeParameterResMessage());
                        break;
                    case PREPARED:
                        senderChargeControlSystem.sendMessage(new SendStartTransactionMessage());
                        break;
                    case START_CHARGING:
                        senderChargeControlSystem.sendMessage(new SetChargeMessage(stateCharacterisation.getPreparedCharge()));
                        stateCharacterisation.setPreparedCharge(null);
                        senderChargeControlSystem.sendMessage(new SendPowerResMessage(true));

                        initSystem.submitChargeTransfer();
                        initSystem.submitMeterValuesSender();
                        stateCharacterisation.setChargingOn(true);
                        break;
                    case EV_CHARGING_STATUS_REQUEST:
                        senderChargeControlSystem.sendMessage(new SendChargingStatusResMessage(senderChargeControlSystem.getMeterValues(), stateCharacterisation.isChargingOn()));
                        break;
                    case STOP_CHARGING:
                        senderChargeControlSystem.sendMessage(new StopChargingByStationMessage());
                        stateCharacterisation.setChargingOn(false);
                        break;
                    case EV_END_SESSION:
                        senderChargeControlSystem.sendMessage(new SendSessionStopResMessage());
                        stateCharacterisation.setEvCharacteristic(null);
                        stateCharacterisation.setState(AVAILABLE);
                        break;
                    /*
                    case CSMS_DECLINE_CHARGE:
                        evCommunicationBlockInterface.sendPowerRes(false);
                        stateCharacterisation.setState(AVAILABLE);
                        break;
                    case EV_DECLINE_CHARGE:
                        csmsComm.sendStopTransaction();
                        stateCharacterisation.setState(AVAILABLE);
                        break;
                    case UNAUTHORIZED:
                        evCommunicationBlockInterface.sendSessionSetupRes(false);
                        while(stateCharacterisation.getState() == UNAUTHORIZED)
                            wait();
                        break;
                        *.
                     */
                    case AVAILABLE:
                        System.out.println("Connector is Available.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public StationState getStationState() {
        return stateCharacterisation;
    }
}