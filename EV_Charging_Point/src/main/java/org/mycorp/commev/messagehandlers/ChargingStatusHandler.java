package org.mycorp.commev.messagehandlers;

import org.mycorp.messages.V2GBodyAbstractType;
import org.mycorp.models.events.common.GetMeterValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ChargingStatusHandler extends V2GMessageHandlerImpl {
    @Override
    public void handleMessage(V2GBodyAbstractType v2gMessageBody) {
        applicationEventPublisher.publishEvent(new GetMeterValues(this, "EVCommunicationBlock"));
    }
}
