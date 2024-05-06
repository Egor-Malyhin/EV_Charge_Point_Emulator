package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.commev.messagefactory.V2GMessageResFactory;
import org.mycorp.models.MeterValues;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.events.chargetransfer.MeterValuesToEV;
import org.mycorp.models.messages.v2g.types.enums.EVSENotification;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;
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
        evCommunicationBlockInterface.sendMessage(V2GMessageResFactory.createChargingStatusRes(ResponseCode.OK, evseNotification, StationCharacteristics.evseId, (long) presentedMeterValues.getSampledValue().get(0).getValue(), StationCharacteristics.meterId));
    }
}
