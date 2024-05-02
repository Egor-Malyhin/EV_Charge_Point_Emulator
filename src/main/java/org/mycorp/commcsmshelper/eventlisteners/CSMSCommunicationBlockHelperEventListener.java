package org.mycorp.commcsmshelper.eventlisteners;

import org.mycorp.commcsmshelper.CSMSCommunicationBlockHelperInterface;
import org.mycorp.stationeventlistener.StationEventListener;
import org.springframework.context.ApplicationEvent;

public abstract class CSMSCommunicationBlockHelperEventListener<T extends ApplicationEvent> implements StationEventListener<T> {
    protected final CSMSCommunicationBlockHelperInterface csmsCommunicationBlockHelperInterface;

    protected CSMSCommunicationBlockHelperEventListener(CSMSCommunicationBlockHelperInterface csmsCommunicationBlockHelperInterface) {
        this.csmsCommunicationBlockHelperInterface = csmsCommunicationBlockHelperInterface;
    }
}
