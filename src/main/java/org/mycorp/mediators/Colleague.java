package org.mycorp.mediators;

import org.mycorp.mediators.receivers.Receiver;
import org.mycorp.mediators.senders.Sender;
import org.springframework.beans.factory.annotation.Autowired;

public class Colleague {
   private Receiver receiver;
   private Sender sender;
   @Autowired
   public Colleague(Receiver receiver, Sender sender){
       this.receiver=receiver;
       this.sender=sender;
   }
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
