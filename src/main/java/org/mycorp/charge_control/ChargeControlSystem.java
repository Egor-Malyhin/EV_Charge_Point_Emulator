package org.mycorp.charge_control;

import org.mycorp.charge_transfer.ChargeTransferBlockInterface;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterface;
import org.mycorp.ev_communication.EvCommunicationBlock;
import static org.mycorp.charge_control.StationStateEnum.*;

import org.mycorp.models.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ChargeControlSystem implements Runnable {
    final CSMSCommunicationBlockInterface csmsComm;
    final ChargeTransferBlockInterface chargeTransferBlockInterface;
    private StationState stateCharacterisation;

    @Autowired
    public ChargeControlSystem(CSMSCommunicationBlockInterface csmsComm, ChargeTransferBlockInterface chargeTransferBlock) {
        this.csmsComm = csmsComm;
        this.chargeTransferBlockInterface = chargeTransferBlock;
        this.stateCharacterisation = new StationState(AVAILABLE, null, null);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Charging Station is Working.");
            try {
                Thread.sleep(1000);
                switch (stateCharacterisation.getState()) {
                    case AUTHORISATION:
                        csmsComm.sendAuthorize(stateCharacterisation.getEvCharacteristic().getIdTag());
                        while (stateCharacterisation.getState() == AUTHORISATION)
                            wait();
                        break;
                    case AUTHORIZE_WAIT_EV_REQUEST:
                        evCommunicationBlockInterface.sendAuthorizeRes(true);
                        while (stateCharacterisation.getState() == AUTHORIZE_WAIT_EV_REQUEST)
                            wait();
                        break;
                    case PREPARED:
                        //stateCharacterisation.setPreparedCharge(new Charge());
                        csmsComm.sendStartTransaction();
                        while (stateCharacterisation.getState() == PREPARED)
                            wait();
                        break;
                    case CHARGING:
                        chargeTransferBlockInterface.setCharge(stateCharacterisation.getPreparedCharge());
                        stateCharacterisation.setPreparedCharge(null);
                        evCommunicationBlockInterface.sendPowerRes();
                        chargingExecutor.submit(chargeTransferBlockThread);
                        while(stateCharacterisation.getState() == CHARGING) {
                            Thread.sleep(5000);
                            csmsComm.sendMeterValues(chargeTransferBlockInterface.getMeterValues());
                            System.out.println("Charging is proceed....");
                        }
                        break;
                    case STOP_CHARGING:
                        csmsComm.sendStopTransaction();
                        while(stateCharacterisation.getState() == STOP_CHARGING)
                            wait();
                        break;
                    case CSMS_DECLINE_CHARGE:
                        evCommunicationBlockInterface.sendCannotStartCharge();
                        setStateCharacterisation(AVAILABLE);
                        break;
                    case EV_DECLINE_CHARGE:
                        csmsComm.sendEvDecline();
                        setStateCharacterisation(AVAILABLE);
                        break;
                    case UNAUTORIZED:
                        evCommunicationBlockInterface.sendAuthorizeRes(false);
                        while(stateCharacterisation.getState() == UNAUTORIZED)
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

    public void setStateCharacterisation(StationStateEnum state){
        this.stateCharacterisation.setState(state);
    }

}