package org.mycorp.mediators.senders;

import org.mycorp.models.station_messages.StationMessage;

public interface Sender {
    void sendMessage(StationMessage message);
}
