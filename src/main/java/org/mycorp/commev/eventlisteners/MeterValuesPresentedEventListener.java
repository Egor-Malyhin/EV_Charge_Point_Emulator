package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.commev.messagebuilders.ChargingStatusResBuilder;
import org.mycorp.models.MeterValues;
import org.mycorp.models.events.chargetransfer.MeterValuesToEV;
import org.mycorp.models.messages.v2g.types.EVSENotification;
import org.mycorp.models.messages.v2g.types.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MeterValuesPresentedEventListener extends EVCommunicationBlockEventListener<MeterValuesToEV> {
    @Autowired
    protected MeterValuesPresentedEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        super(evCommunicationBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(MeterValuesToEV stationEvent) {
        EVSENotification evseNotification = stationEvent.isChargingOn() ? EVSENotification.NONE : EVSENotification.STOPCHARGING;
        MeterValues presentedMeterValues = stationEvent.getMeterValues();
        evCommunicationBlockInterface.sendMessage(buildMessage(new ChargingStatusResBuilder(ResponseCode.OK, evseNotification, presentedMeterValues)));
    }
}
