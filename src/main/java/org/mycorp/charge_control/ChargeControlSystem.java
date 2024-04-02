package org.mycorp.charge_control;

import static org.mycorp.models.StationStateEnum.*;

import org.mycorp.mediators.senders.SenderChargeControlSystem;
import org.mycorp.models.StationState;
import org.mycorp.models.station_messages.charge_transfer_messages.StopChargingByStationMessage;
import org.mycorp.models.station_messages.control_system_messages_charge_transfer.SetChargeMessage;
import org.mycorp.models.station_messages.control_system_messages_csms_comm.SendAuthorizeMessage;
import org.mycorp.models.station_messages.control_system_messages_csms_comm.SendStartTransactionMessage;
import org.mycorp.models.station_messages.ev_comm_messages.*;
import org.springframework.beans.factory.annotation.Autowired;

public class ChargeControlSystem implements Runnable {
    private final SenderChargeControlSystem senderChargeControlSystem;
    private final InitSystem initSystem;
    private final StationState stateCharacterisation;

    @Autowired
    public ChargeControlSystem(InitSystem initSystem, SenderChargeControlSystem senderChargeControlSystem) {
        this.senderChargeControlSystem = senderChargeControlSystem;
        this.initSystem = initSystem;
        this.stateCharacterisation = new StationState(AVAILABLE, null, null, false);
    }

    @Override
    public void run() {
        initSystem.initialize();
        while (true) {
            System.out.println("Charging Station is Working.");
            try {
                Thread.sleep(100);
                switch (stateCharacterisation.getState()) {
                    case AUTHORISATION:
                        senderChargeControlSystem.sendMessage(new SendAuthorizeMessage(stateCharacterisation.getEvCharacteristic().getIdTag()));
                        while (stateCharacterisation.getState() == AUTHORISATION)
                            wait();
                        break;
                    case AUTHORIZED:
                        senderChargeControlSystem.sendMessage(new SendSessionSetupRes(true));
                        while (stateCharacterisation.getState() == AUTHORIZED)
                            wait();
                        break;
                    case WAIT_CHARGING_REQUEST:
                        senderChargeControlSystem.sendMessage(new SendChargeParameterResMessage());
                        while (stateCharacterisation.getState() == WAIT_EV_REQUEST)
                            wait();
                        break;
                    case PREPARED:
                        senderChargeControlSystem.sendMessage(new SendStartTransactionMessage());
                        while (stateCharacterisation.getState() == PREPARED)
                            wait();
                        break;
                    case START_CHARGING:
                        senderChargeControlSystem.sendMessage(new SetChargeMessage(stateCharacterisation.getPreparedCharge()));
                        stateCharacterisation.setPreparedCharge(null);
                        senderChargeControlSystem.sendMessage(new SendPowerResMessage(true));

                        initSystem.submitChargeTransfer();
                        initSystem.submitMeterValuesSender();
                        stateCharacterisation.setChargingOn(true);

                        while(stateCharacterisation.getState() == START_CHARGING)
                            wait();
                        break;
                    case WAIT_EV_REQUEST:
                        while(stateCharacterisation.getState() == WAIT_EV_REQUEST)
                            wait();
                        break;
                    case EV_CHARGING_STATUS_REQUEST:
                        senderChargeControlSystem.sendMessage(new SendChargingStatusResMessage(stateCharacterisation.isChargingOn(), chargeTransferBlockInterface.getMeterValues()));
                        stateCharacterisation.setState(WAIT_EV_REQUEST);
                        break;
                    case STOP_CHARGING:
                        senderChargeControlSystem.sendMessage(new StopChargingByStationMessage());
                        stateCharacterisation.setChargingOn(false);
                        while(stateCharacterisation.getState() == STOP_CHARGING)
                            wait();
                        break;
                    case EV_END_SESSION:
                        senderChargeControlSystem.sendMessage(new SendSessionStopResMessage());
                        stateCharacterisation.setState(AVAILABLE);
                        break;

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
                    case AVAILABLE:
                        stateCharacterisation.setEvCharacteristic(null);
                        System.out.println("Connector is Available");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public StationState getStationState(){
        return stateCharacterisation;
    }
}