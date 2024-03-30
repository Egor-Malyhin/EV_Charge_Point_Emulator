package org.mycorp.models.station_messages.control_system_messages_ev;

import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.StationMessageClassification;

import static org.mycorp.models.station_messages.StationMessageClassification.START_AUTHORIZE;

public class StartAuthorizeMessage extends StationMessage {
    private final byte[] idTag;
    public StartAuthorizeMessage(byte[] idTag) {
        super(START_AUTHORIZE);
        this.idTag = idTag;
    }
    public byte[] getIdTag() {
        return idTag;
    }
}
