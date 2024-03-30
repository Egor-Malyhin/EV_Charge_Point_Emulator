package org.mycorp.mediators;

import org.mycorp.mediators.receivers.ChargeTransferReceiver;
import org.mycorp.models.MeterValues;

public class MediatorChargeTransfer extends MediatorImpl{
    public MeterValues receiveMeterValues(){
        for(Colleague colleague : colleagues)
            if(colleague.getReceiver() instanceof ChargeTransferReceiver)
                return ((ChargeTransferReceiver) colleague.getReceiver()).receiveMeterValues();
        return null;
    }
}
