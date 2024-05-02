package org.mycorp.models.events.commcsms;

import org.mycorp.commcsms.CSMSCommunicationBlock;
import org.springframework.context.ApplicationEvent;

public abstract class CSMSCommunicationBlockEvent extends ApplicationEvent {
    public CSMSCommunicationBlockEvent() {
        super(CSMSCommunicationBlock.class);
    }
}
