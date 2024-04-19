package org.mycorp.csms_communication;

import org.mycorp.csms_communication.message_makers.CoreProfileMessageMaker;
import org.mycorp.models.MeterValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoreProfileMessageMakerImpl implements CoreProfileMessageMaker {

    private final CSMSCommunicationBlock csmsCommunicationBlock;

    @Autowired
    public CoreProfileMessageMakerImpl(CSMSCommunicationBlock csmsCommunicationBlock) {
        this.csmsCommunicationBlock = csmsCommunicationBlock;
    }

    @Override
    public void sendStartTransaction() {


    }

    @Override
    public void sendAuthorize(String idTag) {

    }

    @Override
    public void sendStopTransaction() {

    }

    @Override
    public void sendBootNotification() {

    }

    @Override
    public void sendMeterValues(MeterValues meterValues) {

    }
}
