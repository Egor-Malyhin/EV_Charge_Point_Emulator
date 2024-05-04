package org.mycorp.models.events.commcsms;

import lombok.Getter;
import org.mycorp.models.events.StationEvent;

import java.util.concurrent.CountDownLatch;

@Getter
public abstract class CSMSRequestEvent extends StationEvent {
    private final CountDownLatch countDownLatch;

    public CSMSRequestEvent(Object source, CountDownLatch countDownLatch) {
        super(source);
        this.countDownLatch = countDownLatch;
    }

}
