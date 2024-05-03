package org.mycorp.models.events.commcsms;

import org.springframework.context.ApplicationEvent;

import java.util.concurrent.CountDownLatch;

public class StopChargingByCSMS extends CSMSRequestEvent {
    public StopChargingByCSMS(Object source, CountDownLatch countDownLatch) {
        super(source, countDownLatch);
    }
}
