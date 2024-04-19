package org.mycorp.mediators.receivers;

import org.mycorp.csms_communication.message_makers.CoreProfileMessageMaker;
import org.mycorp.models.station_messages.StationMessage;
import org.mycorp.models.station_messages.control_system_messages_csms_comm.SendAuthorizeMessage;
import org.mycorp.models.station_messages.control_system_messages_csms_comm.SendMeterValuesMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CSMSCommReceiver implements Receiver {
    private final CoreProfileMessageMaker coreProfileMessageMaker;

    @Autowired
    public CSMSCommReceiver(CoreProfileMessageMaker coreProfileMessageMaker) {
        this.coreProfileMessageMaker = coreProfileMessageMaker;
    }

    @Override
    public void receiveMessage(StationMessage message) {
        switch (message.getDescription()) {
            case SEND_START_TRANSACTION:
                coreProfileMessageMaker.sendStartTransaction();
                break;
            case SEND_AUTHORIZE:
                coreProfileMessageMaker.sendAuthorize(((SendAuthorizeMessage) message).getIdTag());
                break;
            case SEND_STOP_TRANSACTION:
                coreProfileMessageMaker.sendStopTransaction();
                break;
            case SEND_METER_VALUES:
                coreProfileMessageMaker.sendMeterValues(((SendMeterValuesMessage) message).getMeterValues());
                break;
            case SEND_BOOT_NOTIFICATION:
                coreProfileMessageMaker.sendBootNotification();
        }
    }
}
