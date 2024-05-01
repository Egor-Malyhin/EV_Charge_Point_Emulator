package org.mycorp.models.events.commcsms;

import org.mycorp.models.events.common.StopCharging;

import java.util.concurrent.CountDownLatch;

public class StopChargingByCSMS extends StopCharging {
    private final CountDownLatch csmsLatch;

    public StopChargingByCSMS(Object source, CountDownLatch csmsLatch) {
        super(source, "CSMSCommunicationBlock");
        this.csmsLatch = csmsLatch;
    }

    public CountDownLatch getCsmsLatch() {
        return csmsLatch;
    }
}
