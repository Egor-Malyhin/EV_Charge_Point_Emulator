package org.mycorp.commcsmshelper.eventlisteners;

import org.mycorp.commcsmshelper.CSMSCommunicationBlockHelperInterface;
import org.mycorp.models.events.chargetransfer.ChargingStopped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("commCSMSHelperChargingStoppedEventListener")
public class ChargingStoppedEventListener extends CSMSCommunicationBlockHelperEventListener<ChargingStopped> {
    @Autowired
    protected ChargingStoppedEventListener(CSMSCommunicationBlockHelperInterface csmsCommunicationBlockHelperInterface) {
        super(csmsCommunicationBlockHelperInterface);
    }


    @Override
    @EventListener
    public void listenEvent(ChargingStopped stationEvent) {
        csmsCommunicationBlockHelperInterface.stopMeterValuesRequester();
    }
}
