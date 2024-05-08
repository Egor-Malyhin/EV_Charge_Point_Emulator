package org.mycorp.commev.eventlisteners;

import org.mycorp.commev.EVCommunicationBlockInterface;
import org.mycorp.commev.messagefactory.V2GMessageResFactory;
import org.mycorp.models.events.commevhelper.StationParametersPresented;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StationParametersPresentedEventListener extends EVCommunicationBlockEventListener<StationParametersPresented> {
    @Autowired
    protected StationParametersPresentedEventListener(EVCommunicationBlockInterface evCommunicationBlockInterface) {
        super(evCommunicationBlockInterface);
    }

    @Override
    @EventListener
    public void listenEvent(StationParametersPresented stationEvent) {
        int ratedVoltageValue = stationEvent.getRatedVoltageValue();
        int maxCurrentValue = stationEvent.getMaxCurrentValue();
        evCommunicationBlockInterface.sendMessage(V2GMessageResFactory.createChargeParameterDiscoveryResMessage(ResponseCode.OK, ratedVoltageValue, maxCurrentValue));
    }
}
