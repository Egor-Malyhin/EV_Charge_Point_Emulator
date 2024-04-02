package org.mycorp.models.station_messages.control_system_messages_csms_comm;


import static org.mycorp.models.station_messages.StationMessageDescription.SEND_AUTHORIZE;

public class SendAuthorizeMessage extends MessageToCSMSComm {
    private final String idTag;

    public SendAuthorizeMessage(String idTag) {
        super(SEND_AUTHORIZE);
        this.idTag = idTag;
    }

    public String getIdTag() {
        return idTag;
    }
}
