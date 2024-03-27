package org.mycorp.csms_communication;

import org.mycorp.models.MeterValues;
import org.springframework.beans.factory.annotation.Autowired;
import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.profile.*;
import eu.chargetime.ocpp.model.*;

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
    public void sendBootNotification() {

    }

    @Override
    public void sendMeterValues(MeterValues meterValues) {

    }
}
