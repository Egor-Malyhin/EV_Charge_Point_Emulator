package org.mycorp.mediators.senders;

import org.mycorp.mediators.MediatorChargeControlSystem;
import org.mycorp.models.MeterValues;
import org.springframework.beans.factory.annotation.Autowired;

public class SenderChargeControlSystem extends SenderImpl{
    @Autowired
    public SenderChargeControlSystem(MediatorChargeControlSystem mediator) {
        super(mediator);
    }
    public MeterValues getMeterValues(){
        return ((MediatorChargeControlSystem) mediator).receiveMeterValues();
    }
}
