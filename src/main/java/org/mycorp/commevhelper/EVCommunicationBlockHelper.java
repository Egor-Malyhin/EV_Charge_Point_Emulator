package org.mycorp.commevhelper;

import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.events.commevhelper.EVDisconnectionAccept;
import org.mycorp.models.events.commevhelper.StationParametersPresented;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


//This module contains the logic for processing those requests from an electric vehicle
//that do not require interaction with other modules of the program to respond to.

//This logic has been moved to a separate module so as not to spoil
//the vertical architecture of the commev module.

//Expand this class if you need to process an additional minor request from EV.
@Component
public class EVCommunicationBlockHelper implements EVCommunicationBlockHelperInterface {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public EVCommunicationBlockHelper(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void presentChargeParameter() {
        applicationEventPublisher.publishEvent(new StationParametersPresented(this, StationCharacteristics.ratedVoltage, StationCharacteristics.maxCurrent));
    }

    @Override
    public void acceptSessionClose() {
        applicationEventPublisher.publishEvent(new EVDisconnectionAccept(this));
    }
}
