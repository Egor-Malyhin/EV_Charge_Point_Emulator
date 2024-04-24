package org.mycorp.commev.messagebuilders;

import org.mycorp.models.messages.v2g.V2GMessage;

public class MessageBuildersDirector {
    private final MessageBuilder messageBuilder;

    public MessageBuildersDirector(MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    public V2GMessage create() {
        return messageBuilder.createBodyType().createBody().createHeader().create();
    }
}
