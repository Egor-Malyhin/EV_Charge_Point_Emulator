package org.mycorp.mediators;

import org.mycorp.models.station_messages.StationMessage;

import java.util.ArrayList;
import java.util.List;

public class MediatorImpl implements Mediator{
    protected List<Colleague> colleagues;

    public MediatorImpl() {
        this.colleagues = new ArrayList<>();
    }

    public List<Colleague> getColleagues() {
        return colleagues;
    }

    public void setColleagues(List<Colleague> colleagues) {
        this.colleagues = colleagues;
    }

    public void addColleague(Colleague colleague){colleagues.add(colleague);
    }

    public void removeColleague(Colleague colleague){
        colleagues.remove(colleague);
    }

    @Override
    public void notify(Sender sender, StationMessage message) {
        for(Colleague colleague : colleagues)
            if(colleague.getSender()!= sender)
                colleague.getReceiver().receiveMessage(message);
    }
}
