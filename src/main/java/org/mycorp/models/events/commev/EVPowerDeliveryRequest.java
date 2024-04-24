package org.mycorp.models.events.commev;

import org.mycorp.models.messages.v2g.types.ChargeProgress;
import org.springframework.context.ApplicationEvent;

public class EVPowerDeliveryRequest extends ApplicationEvent {
    private final ChargeProgress chargeProgress;
    public EVPowerDeliveryRequest(Object source, ChargeProgress chargeProgress) {
        super(source);
        this.chargeProgress = chargeProgress;
    }

    public ChargeProgress getChargeProgress() {
        return chargeProgress;
    }
}
