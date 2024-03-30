package org.mycorp.mediators.receivers;

import org.mycorp.models.station_messages.StationMessage;

public interface Receiver {
    void receiveMessage(StationMessage message);
}
