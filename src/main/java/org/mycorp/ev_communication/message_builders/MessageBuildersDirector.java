package org.mycorp.ev_communication.message_builders;

import org.mycorp.models.v2g_messages.V2GMessage;

public class MessageBuildersDirector {
    private final MessageBuilder messageBuilder;

    public MessageBuildersDirector(MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    public V2GMessage create(){
        return messageBuilder.createBodyType().createBody().createHeader().create();
    }
}
