package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.commev.V2GSessionIdCounter;
import org.mycorp.messagefactory.V2GMessageResFactory;
import org.mycorp.messages.types.enums.EVSENotification;
import org.mycorp.messages.types.enums.ResponseCode;
import org.mycorp.models.MeterValues;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.events.chargetransfer.MeterValuesToEV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MeterValuesToEVEventListener extends EVCommunicationBlockEventListener<MeterValuesToEV> {
    @Autowired
    protected MeterValuesToEVEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        super(evCommunicationBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(MeterValuesToEV stationEvent) {
        EVSENotification evseNotification = stationEvent.isChargingOn() ? EVSENotification.NONE : EVSENotification.STOPCHARGING;
        MeterValues presentedMeterValues = stationEvent.getMeterValues();
        evCommunicationBlockInterface.sendMessage(V2GMessageResFactory.createChargingStatusRes(V2GSessionIdCounter.getInstance().getSessionId(), ResponseCode.OK, evseNotification, StationCharacteristics.evseId, (long) presentedMeterValues.getCharge().getValue(), StationCharacteristics.meterId));
    }
}
