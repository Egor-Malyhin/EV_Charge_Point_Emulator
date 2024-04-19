package org.mycorp.ev_communication.event_listeners;

import org.mycorp.ev_communication.EVCommunicationBlockInterface;
import org.mycorp.ev_communication.message_builders.PowerDeliveryResBuilder;
import org.mycorp.models.station_events.csms_comm_events.CSMSChargingAccept;
import org.mycorp.models.v2g_messages.types.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mycorp.models.v2g_messages.types.EVSENotification.NONE;
import static org.mycorp.models.v2g_messages.types.ResponseCode.FAILED_PowerDelivery_NotApplied;
import static org.mycorp.models.v2g_messages.types.ResponseCode.OK;

public class ChargingAcceptEventListener extends EVCommunicationBlockEventListener<CSMSChargingAccept>{
    @Autowired
    protected ChargingAcceptEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        super(evCommunicationBlockInterface);
    }

    @Override
    public void listenEvent(CSMSChargingAccept stationEvent) {
        ResponseCode responseCode = stationEvent.isAccepted() ? OK : FAILED_PowerDelivery_NotApplied;
        evCommunicationBlockInterface.sendMessage(buildMessage(new PowerDeliveryResBuilder(responseCode, NONE)));
    }
}
