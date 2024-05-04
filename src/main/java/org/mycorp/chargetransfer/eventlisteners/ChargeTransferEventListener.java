package org.mycorp.chargetransfer.eventlisteners;

import org.mycorp.chargetransfer.ChargeTransferBlockInterface;
import org.mycorp.models.events.StationEvent;
import org.mycorp.stationeventlistener.StationEventListener;

public abstract class ChargeTransferEventListener<T extends StationEvent> implements StationEventListener<T> {
    protected final ChargeTransferBlockInterface chargeTransferBlockInterface;

    protected ChargeTransferEventListener(ChargeTransferBlockInterface chargeTransferBlockInterface) {
        this.chargeTransferBlockInterface = chargeTransferBlockInterface;
    }
}
