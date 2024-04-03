package org.mycorp.ev_communication.message_builders;

import org.mycorp.models.v2g_messages.V2GMessage;

public interface MessageBuilder {
    MessageBuilder createBodyType();

    MessageBuilder createBody();

    MessageBuilder createHeader();

    V2GMessage create();
}
