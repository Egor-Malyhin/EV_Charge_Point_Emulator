package org.mycorp.models.events.commcsmshelper;

import org.mycorp.commcsmshelper.CSMSCommunicationBlockHelper;
import org.springframework.context.ApplicationEvent;

public abstract class CSMSCommunicationBlockHelperEvent extends ApplicationEvent {
    public CSMSCommunicationBlockHelperEvent() {
        super(CSMSCommunicationBlockHelper.class);
    }
}
