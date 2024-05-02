package org.mycorp.models.events.evreqlocalmanager;

import org.mycorp.evreqlocalmanager.EVLocalRequestManager;
import org.springframework.context.ApplicationEvent;

public abstract class EVLocalRequestManagerEvent extends ApplicationEvent {
    public EVLocalRequestManagerEvent() {
        super(EVLocalRequestManager.class);
    }
}
