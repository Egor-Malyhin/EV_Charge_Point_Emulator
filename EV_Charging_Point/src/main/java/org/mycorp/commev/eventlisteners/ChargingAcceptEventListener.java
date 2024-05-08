package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.commev.V2GSessionIdCounter;
import org.mycorp.messagefactory.V2GMessageResFactory;
import org.mycorp.messages.types.enums.ResponseCode;
import org.mycorp.models.events.commcsms.CSMSChargingAccept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static org.mycorp.messages.types.enums.EVSENotification.NONE;
import static org.mycorp.messages.types.enums.ResponseCode.FAILED_PowerDelivery_NotApplied;
import static org.mycorp.messages.types.enums.ResponseCode.OK;


@Component
public class ChargingAcceptEventListener extends EVCommunicationBlockEventListener<CSMSChargingAccept> {
    @Autowired
    protected ChargingAcceptEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        super(evCommunicationBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(CSMSChargingAccept stationEvent) {
        ResponseCode responseCode = stationEvent.isAccepted() ? OK : FAILED_PowerDelivery_NotApplied;
        evCommunicationBlockInterface.sendMessage(V2GMessageResFactory.createPowerDeliveryRes(V2GSessionIdCounter.getInstance().getSessionId(), responseCode, NONE));
    }
}
