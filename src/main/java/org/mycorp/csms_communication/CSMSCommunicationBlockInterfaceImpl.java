package org.mycorp.csms_communication;

import org.mycorp.models.MeterValues;
import org.springframework.beans.factory.annotation.Autowired;

public class CSMSCommunicationBlockInterfaceImpl implements CSMSCommunicationBlockInterface{

    private CSMSCommunicationBlock csmsCommunicationBlock;

    @Autowired
    public CSMSCommunicationBlockInterfaceImpl(CSMSCommunicationBlock csmsCommunicationBlock){
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
    public void sendBootNotification(StationConfiguration configuration) {

    }

    @Override
    public void sendMeterValues(MeterValues meterValues) {

    }
}
