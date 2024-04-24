package org.mycorp.commev.messagebuilders;

import org.mycorp.models.messages.v2g.V2GMessage;

public interface MessageBuilder {
    MessageBuilder createBodyType();

    MessageBuilder createBody();

    MessageBuilder createHeader();

    V2GMessage create();
}
