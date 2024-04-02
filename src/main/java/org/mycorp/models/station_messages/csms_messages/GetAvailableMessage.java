package org.mycorp.models.station_messages.csms_messages;

import static org.mycorp.models.station_messages.StationMessageDescription.GET_AVAILABLE;

public class GetAvailableMessage extends MessageFromCSMS{
    public GetAvailableMessage() {
        super(GET_AVAILABLE);
    }
}
