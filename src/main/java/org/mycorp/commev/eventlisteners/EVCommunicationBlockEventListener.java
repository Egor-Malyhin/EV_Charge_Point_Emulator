package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.commev.messagebuilders.MessageBuilder;
import org.mycorp.commev.messagebuilders.MessageBuildersDirector;
import org.mycorp.stationeventlistener.StationEventListener;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.springframework.context.ApplicationEvent;


//if you want to expand the program by adding new listeners extend this class
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
