package org.mycorp.charge_transfer;

import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChargeTransferBlockInterfaceImpl implements ChargeTransferBlockInterface {
    private final ChargeTransferBlock chargeTransferBlock;

    @Autowired
    public ChargeTransferBlockInterfaceImpl(ChargeTransferBlock chargeTransferBlock) {
        this.chargeTransferBlock = chargeTransferBlock;
    }

    @Override
    public MeterValues getMeterValues() {
        return chargeTransferBlock.getMeterValues();
    }

    @Override
    public void setCharge(Charge charge) {
        chargeTransferBlock.setCharge(charge);
    }
}
