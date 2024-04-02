package org.mycorp.mediators;

import org.mycorp.mediators.senders.Sender;
import org.mycorp.models.station_messages.StationMessage;

public interface Mediator {
    void notify(Sender sender, StationMessage message);
    void addColleague(Colleague colleague);
}
