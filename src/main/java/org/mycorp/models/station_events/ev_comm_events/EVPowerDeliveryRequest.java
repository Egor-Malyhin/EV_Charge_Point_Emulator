package org.mycorp.models.station_events.ev_comm_events;

import org.mycorp.models.v2g_messages.types.ChargeProgress;
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
