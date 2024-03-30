package org.mycorp.charge_control;

import org.mycorp.charge_transfer.ChargeTransferBlock;
import org.mycorp.charge_transfer.ChargeTransferBlockInterface;
import org.mycorp.csms_communication.CSMSCommunicationBlock;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterface;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterfaceImpl;
import org.mycorp.ev_communication.EvCommunicationBlock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InitSystem {
    final private ChargeTransferBlock chargeTransferBlock;
    final private EvCommunicationBlock evCommunicationBlock;
    final private CSMSCommunicationBlock csmsCommunicationBlock;
    final private MeterValuesSender meterValuesSender;
    final private ExecutorService chargingExecutor;

    @Autowired
    public InitSystem(EvCommunicationBlock evCommunicationBlock, ChargeTransferBlock chargeTransferBlock, CSMSCommunicationBlock csmsCommunicationBlock, MeterValuesSender meterValuesSender) {
        this.evCommunicationBlock = evCommunicationBlock;
        this.chargeTransferBlock = chargeTransferBlock;
        this.csmsCommunicationBlock = csmsCommunicationBlock;
        this.meterValuesSender = meterValuesSender;
        this.chargingExecutor = Executors.newFixedThreadPool(4);
    }

    public void initialize(){
        chargingExecutor.submit(evCommunicationBlock);
    }

    public void submitChargeTransfer(){
        chargingExecutor.submit(chargeTransferBlock);
    }

    public void submitMeterValuesSender(){
        chargingExecutor.submit(meterValuesSender);
    }

    public void stopChargeTransfer(){
        meterValuesSender.stop();
        chargeTransferBlock.stop();
    }

    public void cancelThreads(){
        chargingExecutor.shutdown();
    }

}
