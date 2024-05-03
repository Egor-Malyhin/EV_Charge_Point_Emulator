package org.mycorp.models.events.common;

public class GetMeterValues extends CommonEvent {
    public GetMeterValues(Object source, String requesterBlock) {
        super(source, requesterBlock);
    }
}
