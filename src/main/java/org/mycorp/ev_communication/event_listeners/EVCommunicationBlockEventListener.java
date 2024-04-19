package org.mycorp.ev_communication.event_listeners;

import org.mycorp.ev_communication.EVCommunicationBlockInterface;
import org.mycorp.ev_communication.message_builders.MessageBuilder;
import org.mycorp.ev_communication.message_builders.MessageBuildersDirector;
import org.mycorp.main.StationEventListener;
import org.mycorp.models.v2g_messages.V2GMessage;
import org.springframework.context.ApplicationEvent;

public abstract class EVCommunicationBlockEventListener<T extends ApplicationEvent> implements StationEventListener<T> {
    protected final EVCommunicationBlockInterface evCommunicationBlockInterface;

    protected EVCommunicationBlockEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        this.evCommunicationBlockInterface = evCommunicationBlockInterface;
    }

    protected V2GMessage buildMessage(MessageBuilder builder){
       MessageBuildersDirector director = new MessageBuildersDirector(builder);
       return director.create();
    }
}
