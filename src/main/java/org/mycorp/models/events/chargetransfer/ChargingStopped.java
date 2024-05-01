package org.mycorp.models.events.chargetransfer;

import org.mycorp.models.events.common.StopCharging;
import org.springframework.context.ApplicationEvent;

public class ChargingStopped extends StopCharging {
    public ChargingStopped(Object source, String shutdownInitiator) {
        super(source, shutdownInitiator);
    }
}
