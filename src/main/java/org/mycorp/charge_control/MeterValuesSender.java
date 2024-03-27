package org.mycorp.charge_control;

import org.mycorp.charge_transfer.ChargeTransferBlockInterface;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class MeterValuesSender implements Runnable{

    private final CSMSCommunicationBlockInterface csmsCommunicationBlockInterface;
    private final ChargeTransferBlockInterface chargeTransferBlockInterface;
    private boolean isRunning;
    @Autowired
    public MeterValuesSender(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ChargeTransferBlockInterface chargeTransferBlockInterface){
        this.csmsCommunicationBlockInterface = csmsCommunicationBlockInterface;
        this.chargeTransferBlockInterface = chargeTransferBlockInterface;
        this.isRunning=true;
    }
    @Override
    public void run() {
        while(isRunning){
            try {
                Thread.sleep(5000);
                csmsCommunicationBlockInterface.sendMeterValues(chargeTransferBlockInterface.getMeterValues());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void stop(){
        isRunning=false;
    }
}
