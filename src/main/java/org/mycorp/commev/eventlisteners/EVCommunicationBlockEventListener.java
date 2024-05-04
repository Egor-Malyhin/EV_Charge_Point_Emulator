package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.models.events.StationEvent;
import org.mycorp.stationeventlistener.StationEventListener;


//if you want to expand the program by adding new listeners extend this class
public abstract class EVCommunicationBlockEventListener<T extends StationEvent> implements StationEventListener<T> {
    protected final EVCommunicationBlockInterface evCommunicationBlockInterface;

    protected EVCommunicationBlockEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        this.evCommunicationBlockInterface = evCommunicationBlockInterface;
    }
}
