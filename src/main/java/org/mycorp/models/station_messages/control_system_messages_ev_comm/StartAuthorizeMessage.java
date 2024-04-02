package org.mycorp.models.station_messages.control_system_messages_ev_comm;

import static org.mycorp.models.station_messages.StationMessageDescription.START_AUTHORIZE;

public class StartAuthorizeMessage extends MessageToEVComm {
    private final byte[] idTag;

    public StartAuthorizeMessage(byte[] idTag) {
        super(START_AUTHORIZE);
        this.idTag = idTag;
    }

    public byte[] getIdTag() {
        return idTag;
    }
}
