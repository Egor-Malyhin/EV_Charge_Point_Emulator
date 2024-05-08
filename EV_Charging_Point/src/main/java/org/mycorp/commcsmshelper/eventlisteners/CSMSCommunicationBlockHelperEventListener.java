package org.mycorp.commcsmshelper.eventlisteners;

import org.mycorp.commcsmshelper.CSMSCommunicationBlockHelperInterface;
import org.mycorp.models.events.StationEvent;
import org.mycorp.stationeventlistener.StationEventListener;

public abstract class CSMSCommunicationBlockHelperEventListener<T extends StationEvent> implements StationEventListener<T> {
    protected final CSMSCommunicationBlockHelperInterface csmsCommunicationBlockHelperInterface;

    protected CSMSCommunicationBlockHelperEventListener(CSMSCommunicationBlockHelperInterface csmsCommunicationBlockHelperInterface) {
        this.csmsCommunicationBlockHelperInterface = csmsCommunicationBlockHelperInterface;
    }
}
