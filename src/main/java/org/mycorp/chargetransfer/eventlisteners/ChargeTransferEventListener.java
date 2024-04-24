package org.mycorp.chargetransfer.eventlisteners;

import org.mycorp.chargetransfer.ChargeTransferBlockInterface;
import org.mycorp.main.StationEventListener;
import org.springframework.context.ApplicationEvent;

public abstract class ChargeTransferEventListener<T extends ApplicationEvent> implements StationEventListener<T> {
    protected final ChargeTransferBlockInterface chargeTransferBlockInterface;

    protected ChargeTransferEventListener(ChargeTransferBlockInterface chargeTransferBlockInterface) {
        this.chargeTransferBlockInterface = chargeTransferBlockInterface;
    }
}
