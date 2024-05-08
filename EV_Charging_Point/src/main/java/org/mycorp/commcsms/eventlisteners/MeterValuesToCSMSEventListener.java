package org.mycorp.commcsms.eventlisteners;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.model.core.MeterValuesRequest;
import eu.chargetime.ocpp.model.core.SampledValue;
import org.mycorp.commcsms.CSMSCommunicationBlockInterface;
import org.mycorp.models.MeterValues;
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
        MeterValues meterValues = stationEvent.getMeterValues();

        SampledValue sampledValue = new SampledValue(String.valueOf(meterValues.getCharge().getValue()));
        sampledValue.setUnit(meterValues.getCharge().getUnit());
        ZonedDateTime timeStamp = meterValues.getTimestamp().atZone(StationVariables.getInstance().getZoneId());
        MeterValuesRequest meterValuesRequest = clientCoreProfileMessageGenerator.createMeterValuesRequest(StationCharacteristics.connectorId, timeStamp, sampledValue);
        meterValuesRequest.setTransactionId(StationVariables.getInstance().getTransactionId());
        csmsCommunicationBlockInterface.addToMessageQueue(meterValuesRequest);
    }
}
