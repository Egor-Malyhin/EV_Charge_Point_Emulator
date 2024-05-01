package org.mycorp.chargetransfer;

import org.mycorp.models.Charge;

public interface ChargeTransferBlockInterface {
    void startChargeTransfer();

    void presentMeterValues(String requester);

    void setCharge(Charge charge);

    void stopChargeTransfer(String shutdownInitiator);
}
