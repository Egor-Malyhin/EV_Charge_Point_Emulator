package org.mycorp.models.events.common;

public class StopCharging extends CommonEvent {
    public StopCharging(Object source, String requesterBlock) {
        super(source, requesterBlock);
    }
}
