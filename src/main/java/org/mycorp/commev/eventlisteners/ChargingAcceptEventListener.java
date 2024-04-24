package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.commev.messagebuilders.PowerDeliveryResBuilder;
import org.mycorp.models.events.commcsms.CSMSChargingAccept;
import org.mycorp.models.messages.v2g.types.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static org.mycorp.models.messages.v2g.types.EVSENotification.NONE;
import static org.mycorp.models.messages.v2g.types.ResponseCode.FAILED_PowerDelivery_NotApplied;
import static org.mycorp.models.messages.v2g.types.ResponseCode.OK;

@Component
public class ChargingAcceptEventListener extends EVCommunicationBlockEventListener<CSMSChargingAccept>{
    @Autowired
    protected ChargingAcceptEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        super(evCommunicationBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(CSMSChargingAccept stationEvent) {
        ResponseCode responseCode = stationEvent.isAccepted() ? OK : FAILED_PowerDelivery_NotApplied;
        evCommunicationBlockInterface.sendMessage(buildMessage(new PowerDeliveryResBuilder(responseCode, NONE)));
    }
}
