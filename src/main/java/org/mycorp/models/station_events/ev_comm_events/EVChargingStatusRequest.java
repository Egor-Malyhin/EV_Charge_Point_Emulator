package org.mycorp.models.station_events.ev_comm_events;

import org.mycorp.models.v2g_messages.types.ChargeProgress;
import org.springframework.context.ApplicationEvent;

public class EVChargingStatusRequest extends ApplicationEvent {
    public EVChargingStatusRequest(Object source) {
        super(source);
    }
}
