package org.mycorp.models.events.commcsms;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.concurrent.CountDownLatch;

@Getter
public abstract class CSMSRequestEvent extends ApplicationEvent {
    private final CountDownLatch countDownLatch;

    public CSMSRequestEvent(Object source, CountDownLatch countDownLatch) {
        super(source);
        this.countDownLatch = countDownLatch;
    }

}
