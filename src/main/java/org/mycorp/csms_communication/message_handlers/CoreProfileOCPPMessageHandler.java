package org.mycorp.csms_communication.message_handlers;

import org.mycorp.models.ocpp_messages.OCPPMessage;
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
