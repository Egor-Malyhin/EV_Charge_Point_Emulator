package org.mycorp.models.station_messages.csms_messages;

import static org.mycorp.models.station_messages.StationMessageDescription.AUTHORIZED;

public class AuthorizedMessage extends MessageFromCSMS{
    public AuthorizedMessage() {
        super(AUTHORIZED);
    }
}
