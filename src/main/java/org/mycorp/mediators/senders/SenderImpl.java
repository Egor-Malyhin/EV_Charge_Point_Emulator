package org.mycorp.mediators.senders;

import org.mycorp.mediators.Mediator;
import org.mycorp.models.station_messages.StationMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class SenderImpl implements Sender {
    protected final Mediator mediator;

    @Autowired
    public SenderImpl(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void sendMessage(StationMessage message) {
        mediator.notify(this, message);
    }
}
