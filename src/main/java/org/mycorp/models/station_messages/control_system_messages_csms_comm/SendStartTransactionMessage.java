package org.mycorp.models.station_messages.control_system_messages_csms_comm;

import static org.mycorp.models.station_messages.StationMessageDescription.SEND_START_TRANSACTION;

public class SendStartTransactionMessage extends MessageToCSMSComm{
    public SendStartTransactionMessage() {
        super(SEND_START_TRANSACTION);
    }
}
