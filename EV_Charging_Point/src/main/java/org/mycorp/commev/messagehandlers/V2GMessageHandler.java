package org.mycorp.commev.messagehandlers;


import org.mycorp.messages.V2GBodyAbstractType;

//Did not use generics because it is difficult to perform substitution in the
//EVCommunicationBlockSessionHandler class
public interface V2GMessageHandler {
    void handleMessage(V2GBodyAbstractType v2gMessageBody);
}
