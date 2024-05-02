package org.mycorp.models.events.chargetransfer;

import org.mycorp.chargetransfer.ChargeTransferBlock;
import org.springframework.context.ApplicationEvent;

public abstract class ChargeTransferEvent extends ApplicationEvent {
    public ChargeTransferEvent() {
        super(ChargeTransferBlock.class);
    }
}
