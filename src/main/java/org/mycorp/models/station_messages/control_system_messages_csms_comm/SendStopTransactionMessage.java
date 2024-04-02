package org.mycorp.models.station_messages.control_system_messages_csms_comm;

import static org.mycorp.models.station_messages.StationMessageDescription.SEND_STOP_TRANSACTION;

public class SendStopTransactionMessage extends MessageToCSMSComm{
    public SendStopTransactionMessage() {
        super(SEND_STOP_TRANSACTION);
    }
}
