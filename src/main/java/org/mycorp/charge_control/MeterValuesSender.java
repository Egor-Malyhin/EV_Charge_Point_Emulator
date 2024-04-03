package org.mycorp.charge_control;

import org.mycorp.mediators.senders.SenderChargeControlSystem;
import org.mycorp.models.station_messages.control_system_messages_csms_comm.SendMeterValuesMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MeterValuesSender implements Runnable {
    private final SenderChargeControlSystem senderChargeControlSystem;
    private boolean isRunning;

    @Autowired
    public MeterValuesSender(@Qualifier("senderChargeControlSystem") SenderChargeControlSystem senderChargeControlSystem) {
        this.senderChargeControlSystem = senderChargeControlSystem;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(5000);
                senderChargeControlSystem.sendMessage(new SendMeterValuesMessage(senderChargeControlSystem.getMeterValues()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
