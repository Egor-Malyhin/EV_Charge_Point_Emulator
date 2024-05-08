package org.mycorp.commcsms.eventlisteners;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.model.Request;
import org.mycorp.commcsms.CSMSCommunicationBlockInterface;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.StationVariables;
import org.mycorp.models.events.chargetransfer.MeterValuesToCSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class MeterValuesToCSMSEventListener extends CSMSCommunicationBlockEventListener<MeterValuesToCSMS> {
    @Autowired
    protected MeterValuesToCSMSEventListener(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface, ClientCoreProfile clientCoreProfileMessageGenerator) {
        super(csmsCommunicationBlockInterface, clientCoreProfileMessageGenerator);
    }

    @Override
    @EventListener
    public void listenEvent(MeterValuesToCSMS stationEvent) {
        String meterValues = String.valueOf(stationEvent.getMeterValues().getSampledValue().get(0).getValue());
        ZonedDateTime timeStamp = stationEvent.getMeterValues().getTimestamp().atZone(StationVariables.getInstance().getZoneId());
        Request meterValuesRequest = clientCoreProfileMessageGenerator.createMeterValuesRequest(StationCharacteristics.connectorId, timeStamp, meterValues);
        csmsCommunicationBlockInterface.addToMessageQueue(meterValuesRequest);
    }
}
