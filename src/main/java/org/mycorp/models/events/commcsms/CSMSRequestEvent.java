package org.mycorp.models.events.commcsms;

import java.util.concurrent.CountDownLatch;

public abstract class CSMSRequestEvent extends CSMSCommunicationBlockEvent {
    private final CountDownLatch countDownLatch;

    public CSMSRequestEvent(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
