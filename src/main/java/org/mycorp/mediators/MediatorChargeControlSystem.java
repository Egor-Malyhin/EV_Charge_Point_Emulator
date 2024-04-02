package org.mycorp.mediators;

import org.mycorp.models.MeterValues;

public interface MediatorChargeControlSystem extends Mediator{
    MeterValues receiveMeterValues();
}
