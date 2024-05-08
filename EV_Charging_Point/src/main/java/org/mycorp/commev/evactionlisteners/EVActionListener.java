package org.mycorp.commev.evactionlisteners;

import org.mycorp.models.StationVariables;
import org.mycorp.models.events.commev.ConnectedEV;
import org.mycorp.models.events.commev.DisconnectedEV;
import org.mycorp.stationeventpublisher.StationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

//A class that generates events related to the actions of an electric vehicle without regard to protocols
//(connection and disconnection from the station)

//I did not make it possible to expand as with message handlers
//(description of all cases in different classes and adding a context class),
//since all other interactions with an electric car occur according to the protocol.
@Component
public class EVActionListener extends StationEventPublisher {
    @Autowired
    protected EVActionListener(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    public void evConnected() {
        applicationEventPublisher.publishEvent(new ConnectedEV(this));
    }

    public void evDisconnected(boolean isDisconnectionAccept) {
        StationVariables stationVariables = StationVariables.getInstance();
        stationVariables.setIdTag(null);
        applicationEventPublisher.publishEvent(new DisconnectedEV(this, isDisconnectionAccept));
    }
}
