package org.mycorp.commcsmshelper.eventlisteners;

import org.mycorp.commcsmshelper.CSMSCommunicationBlockHelperInterface;
import org.mycorp.models.events.commcsms.CSMSChargingAccept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("commCSMSHelperCSMSChargingAcceptEventListener")
public class CSMSChargingAcceptEventListener extends CSMSCommunicationBlockHelperEventListener<CSMSChargingAccept> {

    @Autowired
    protected CSMSChargingAcceptEventListener(CSMSCommunicationBlockHelperInterface csmsCommunicationBlockHelperInterface) {
        super(csmsCommunicationBlockHelperInterface);
    }

    @Override
    @EventListener
    public void listenEvent(CSMSChargingAccept stationEvent) {
        if (stationEvent.isAccepted())
            csmsCommunicationBlockHelperInterface.startMeterValuesRequester();
    }
}
