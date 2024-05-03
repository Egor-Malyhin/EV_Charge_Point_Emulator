package org.mycorp.models.events.commcsms;

import java.util.concurrent.CountDownLatch;

public class StopChargingByCSMS extends CSMSRequestEvent {
    public StopChargingByCSMS(Object source, CountDownLatch countDownLatch) {
        super(source, countDownLatch);
    }
}
