package org.mycorp.charge_control;

import org.mycorp.charge_transfer.ChargeTransferBlockInterface;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterface;

import static org.mycorp.models.StationStateEnum.*;

import org.mycorp.ev_communication.EvCommunicationBlockInterface;
import org.mycorp.models.StationState;
import org.springframework.beans.factory.annotation.Autowired;



public class ChargeControlSystem implements Runnable {
    private final CSMSCommunicationBlockInterface csmsComm;
    private final EvCommunicationBlockInterface evCommunicationBlockInterface;
    private final InitSystem initSystem;
    private final ChargeTransferBlockInterface chargeTransferBlockInterface;
    private final StationState stateCharacterisation;

    @Autowired
    public ChargeControlSystem(CSMSCommunicationBlockInterface csmsComm, EvCommunicationBlockInterface evCommunicationBlockInterface, InitSystem initSystem, ChargeTransferBlockInterface chargeTransferBlock) {
        this.csmsComm = csmsComm;
        this.evCommunicationBlockInterface = evCommunicationBlockInterface;
        this.initSystem = initSystem;
        this.chargeTransferBlockInterface = chargeTransferBlock;
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
                        csmsComm.sendAuthorize(stateCharacterisation.getEvCharacteristic().getIdTag());
                        while (stateCharacterisation.getState() == AUTHORISATION)
                            wait();
                        break;
                    case AUTHORIZED:
                        evCommunicationBlockInterface.sendSessionSetupRes(true);
                        while (stateCharacterisation.getState() == AUTHORIZED)
                            wait();
                        break;
                    case WAIT_CHARGING_REQUEST:
                        evCommunicationBlockInterface.sendChargeParameterRes();
                        while (stateCharacterisation.getState() == WAIT_EV_REQUEST)
                            wait();
                        break;
                    case PREPARED:
                        csmsComm.sendStartTransaction();
                        while (stateCharacterisation.getState() == PREPARED)
                            wait();
                        break;
                    case START_CHARGING:
                        chargeTransferBlockInterface.setCharge(stateCharacterisation.getPreparedCharge());
                        stateCharacterisation.setPreparedCharge(null);
                        evCommunicationBlockInterface.sendPowerRes(true);
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
                        evCommunicationBlockInterface.sendChargingStatusRes(stateCharacterisation.isChargingOn(), chargeTransferBlockInterface.getMeterValues());
                        stateCharacterisation.setState(WAIT_EV_REQUEST);
                        break;
                    case STOP_CHARGING:
                        csmsComm.sendStopTransaction();
                        stateCharacterisation.setChargingOn(false);
                        while(stateCharacterisation.getState() == STOP_CHARGING)
                            wait();
                        break;
                    case EV_END_SESSION:
                        evCommunicationBlockInterface.sendSessionStopRes();
                        stateCharacterisation.setState(AVAILABLE);
                        break;

                    case CSMS_DECLINE_CHARGE:
                        evCommunicationBlockInterface.sendPowerRes(false);
                        setStateCharacterisation(AVAILABLE);
                        break;
                    case EV_DECLINE_CHARGE:
                        csmsComm.sendEvDecline();
                        setStateCharacterisation(AVAILABLE);
                        break;
                    case UNAUTHORIZED:
                        evCommunicationBlockInterface.sendSessionSetupRes(false);
                        while(stateCharacterisation.getState() == UNAUTHORIZED)
                            wait();
                        break;
                    case AVAILABLE:
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