package org.mycorp.chargetransfer;

import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;

public interface ChargeTransferBlockInterface {
    void startChargeTransfer();

    void presentMeterValues(String requester);

    void setCharge(Charge charge);

    void stopChargeTransfer(String shutdownInitiator);
}
