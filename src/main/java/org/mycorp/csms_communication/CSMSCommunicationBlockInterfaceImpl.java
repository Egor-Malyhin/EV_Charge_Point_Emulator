package org.mycorp.csms_communication;

import org.mycorp.models.MeterValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CSMSCommunicationBlockInterfaceImpl implements CSMSCommunicationBlockInterface {

    private final CSMSCommunicationBlockClient csmsCommunicationBlockClient;

    @Autowired
    public CSMSCommunicationBlockInterfaceImpl(CSMSCommunicationBlockClient csmsCommunicationBlockClient) {
        this.csmsCommunicationBlockClient = csmsCommunicationBlockClient;
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
