package org.mycorp.charge_transfer;

import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;

public interface ChargeTransferBlockInterface {
    MeterValues getMeterValues();

    void setCharge(Charge charge);
}
