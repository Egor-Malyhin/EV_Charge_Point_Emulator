package org.mycorp.models.events.commcsms;

import org.mycorp.models.events.common.StopCharging;

import java.util.concurrent.CountDownLatch;

public class StopChargingByCSMS extends CSMSRequestEvent {
    public StopChargingByCSMS(CountDownLatch countDownLatch) {
        super(countDownLatch);
    }
}
