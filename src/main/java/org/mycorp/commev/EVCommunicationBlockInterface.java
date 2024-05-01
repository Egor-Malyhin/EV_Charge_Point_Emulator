package org.mycorp.commev;

import org.mycorp.models.messages.v2g.V2GMessage;

public interface EVCommunicationBlockInterface {
    void sendMessage(V2GMessage v2gMessage);
}
