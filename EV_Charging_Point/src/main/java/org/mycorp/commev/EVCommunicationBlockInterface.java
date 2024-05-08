package org.mycorp.commev;


import org.mycorp.messages.V2GMessage;

public interface EVCommunicationBlockInterface {
    void sendMessage(V2GMessage v2gMessage);

    void availableConnection();

    void acceptDisconnect();
}
