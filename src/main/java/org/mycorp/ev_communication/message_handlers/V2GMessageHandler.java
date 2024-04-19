package org.mycorp.ev_communication.message_handlers;

import org.mycorp.models.v2g_messages.V2GBodyAbstractType;

//Did not use generics because it is difficult to perform substitution in the
//EVCommunicationBlockSessionHandler class
public interface V2GMessageHandler {
    void handleMessage(V2GBodyAbstractType v2gMessageBody);
}
