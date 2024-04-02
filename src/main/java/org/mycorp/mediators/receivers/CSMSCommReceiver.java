package org.mycorp.mediators.receivers;

import org.mycorp.csms_communication.CSMSCommunicationBlockInterface;
import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.control_system_messages_csms_comm.SendAuthorizeMessage;
import org.mycorp.models.station_messages.control_system_messages_csms_comm.SendMeterValuesMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class CSMSCommReceiver implements Receiver{
    private final CSMSCommunicationBlockInterface csmsCommunicationBlockInterface;
    @Autowired
    public CSMSCommReceiver(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface){
        this.csmsCommunicationBlockInterface = csmsCommunicationBlockInterface;
    }
    @Override
    public void receiveMessage(StationMessage message) {
        switch(message.getDescription()){
            case SEND_START_TRANSACTION:
                csmsCommunicationBlockInterface.sendStartTransaction();
                break;
            case SEND_AUTHORIZE:
                csmsCommunicationBlockInterface.sendAuthorize(((SendAuthorizeMessage) message).getIdTag());
                break;
            case SEND_STOP_TRANSACTION:
                csmsCommunicationBlockInterface.sendStopTransaction();
                break;
            case SEND_METER_VALUES:
                csmsCommunicationBlockInterface.sendMeterValues(((SendMeterValuesMessage) message).getMeterValues());
                break;
            case SEND_BOOT_NOTIFICATION:
                csmsCommunicationBlockInterface.sendBootNotification();
        }
    }
}
