package org.mycorp.models.station_messages.control_system_messages_csms_comm;

import static org.mycorp.models.station_messages.StationMessageDescription.SEND_BOOT_NOTIFICATION;

public class SendBootNotificationMessage extends MessageToCSMSComm{
    public SendBootNotificationMessage() {
        super(SEND_BOOT_NOTIFICATION);
    }
}
