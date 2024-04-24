package org.mycorp.commcsms.message_handlers;

import org.mycorp.models.messages.ocpp.OCPPMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("coreProfileOCPPMessageHandler")
public class CoreProfileOCPPMessageHandler implements OCPPMessageHandler {
    @Autowired
    private ApplicationEventPublisher ocppPublisher;

    @Override
    public void handleMessage(OCPPMessage message) {

    }
}
