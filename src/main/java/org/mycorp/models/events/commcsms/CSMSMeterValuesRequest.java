package org.mycorp.models.events.commcsms;

import org.springframework.context.ApplicationEvent;

public class CSMSMeterValuesRequest extends ApplicationEvent {
    public CSMSMeterValuesRequest(Object source) {
        super(source);
    }
}
