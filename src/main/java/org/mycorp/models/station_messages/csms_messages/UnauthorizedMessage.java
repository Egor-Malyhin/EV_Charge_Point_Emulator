package org.mycorp.models.station_messages.csms_messages;

import static org.mycorp.models.station_messages.StationMessageDescription.UNAUTHORIZED;

public class UnauthorizedMessage extends MessageFromCSMS {
    public UnauthorizedMessage() {
        super(UNAUTHORIZED);
    }
}
