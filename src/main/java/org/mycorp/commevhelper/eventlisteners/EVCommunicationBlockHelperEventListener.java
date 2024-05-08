package org.mycorp.commevhelper.eventlisteners;

import org.mycorp.commevhelper.EVCommunicationBlockHelperInterface;
import org.mycorp.models.events.StationEvent;
import org.mycorp.stationeventlistener.StationEventListener;

public abstract class EVCommunicationBlockHelperEventListener<T extends StationEvent> implements StationEventListener<T> {
    protected final EVCommunicationBlockHelperInterface evCommunicationBlockHelperInterface;

    protected EVCommunicationBlockHelperEventListener(EVCommunicationBlockHelperInterface evCommunicationBlockHelperInterface) {
        this.evCommunicationBlockHelperInterface = evCommunicationBlockHelperInterface;
    }
}
