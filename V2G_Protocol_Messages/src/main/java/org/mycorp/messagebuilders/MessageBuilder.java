package org.mycorp.messagebuilders;

import org.mycorp.messages.V2GMessage;

public interface MessageBuilder {
    MessageBuilder createBodyType();

    MessageBuilder createBody();

    MessageBuilder createHeader();

    V2GMessage create();
}
