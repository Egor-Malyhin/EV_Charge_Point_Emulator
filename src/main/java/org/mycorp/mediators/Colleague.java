package org.mycorp.mediators;

import org.mycorp.mediators.receivers.Receiver;

public class Colleague {
   private Receiver receiver;
   private Sender sender;

    public Receiver getReceiver() {
        return receiver;
    }

    public Sender getSender() {
        return sender;
    }
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
