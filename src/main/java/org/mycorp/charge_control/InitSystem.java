package org.mycorp.charge_control;

import org.mycorp.charge_transfer.ChargeTransferBlock;
import org.mycorp.charge_transfer.ChargeTransferBlockInterface;
import org.mycorp.csms_communication.CSMSCommunicationBlockInterface;
import org.mycorp.ev_communication.EvCommunicationBlock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InitSystem {
    final private ChargeTransferBlock chargeTransferBlock;
    final private EvCommunicationBlock evCommunicationBlock;
    final private ChargeControlSystem chargeControlSystem;
    final private ExecutorService chargingExecutor = Executors.newFixedThreadPool(4);

    @Autowired
    public InitSystem(EvCommunicationBlock evCommunicationBlock, ChargeControlSystem chargeControlSystem, ChargeTransferBlock chargeTransferBlock) {
        this.evCommunicationBlock = evCommunicationBlock;
        this.chargeControlSystem = chargeControlSystem;
        this.chargeTransferBlock = chargeTransferBlock;
    }

    public void initialize(){
        chargingExecutor.submit(chargeControlSystem);
        chargingExecutor.submit(evCommunicationBlock);
    }

    public void submitChargeTransfer(){
        chargingExecutor.submit(chargeTransferBlock);
    }

}
