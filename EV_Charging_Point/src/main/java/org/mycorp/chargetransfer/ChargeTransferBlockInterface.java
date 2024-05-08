package org.mycorp.chargetransfer;

import org.mycorp.models.Charge;

public interface ChargeTransferBlockInterface {
    void startChargeTransfer();

    void presentMeterValues(String requester);

    void setPreparedCharge(Charge preparedCharge);

    void stopChargeTransferNormally();

    void stopChargeTransferEmergency();
}
