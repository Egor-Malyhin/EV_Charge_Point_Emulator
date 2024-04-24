package org.mycorp.commcsms.message_makers;

import org.mycorp.commcsms.CSMSCommunicationBlockInterface;
import org.mycorp.models.MeterValues;
import org.springframework.beans.factory.annotation.Autowired;

public class CoreProfileMessageMakerImpl extends OCPPMessageMaker implements CoreProfileMessageMaker{
    @Autowired
    protected CoreProfileMessageMakerImpl(CSMSCommunicationBlockInterface csmsCommunicationBlockInterface) {
        super(csmsCommunicationBlockInterface);
    }

    @Override
    public void sendStartTransaction() {
        //csmsCommunicationBlockInterface.addToMessageQueue();
    }

    @Override
    public void sendAuthorize(String idTag) {

    }

    @Override
    public void sendStopTransaction() {

    }

    @Override
    public void sendMeterValues(MeterValues meterValues) {

    }

    @Override
    public void sendBootNotification() {

    }
}
