package org.mycorp.charge_control;

import org.mycorp.charge_transfer.ChargeTransferBlock;
import org.mycorp.csms_communication.CSMSCommunicationBlockClient;
import org.mycorp.ev_communication.EVCommunicationBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class InitSystem {
    final private ChargeTransferBlock chargeTransferBlock;
    final private EVCommunicationBlock evCommunicationBlock;
    final private CSMSCommunicationBlockClient csmsCommunicationBlockClient;
    final private MeterValuesSender meterValuesSender;
    final private ExecutorService chargingExecutor;

    @Autowired
    public InitSystem(EVCommunicationBlock evCommunicationBlock, ChargeTransferBlock chargeTransferBlock, CSMSCommunicationBlockClient csmsCommunicationBlockClient, MeterValuesSender meterValuesSender) {
        this.evCommunicationBlock = evCommunicationBlock;
        this.chargeTransferBlock = chargeTransferBlock;
        this.csmsCommunicationBlockClient = csmsCommunicationBlockClient;
        this.meterValuesSender = meterValuesSender;
        this.chargingExecutor = Executors.newFixedThreadPool(4);
    }

    public void initialize() {
        chargingExecutor.submit(evCommunicationBlock);
    }

    public void submitChargeTransfer() {
        chargingExecutor.submit(chargeTransferBlock);
    }

    public void submitMeterValuesSender() {
        chargingExecutor.submit(meterValuesSender);
    }

    public void stopChargeTransfer() {
        meterValuesSender.stop();
        chargeTransferBlock.stop();
    }

    public void cancelThreads() {
        chargingExecutor.shutdown();
    }

}
