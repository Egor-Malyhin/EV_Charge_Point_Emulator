package org.mycorp.commcsms.message_handlers;

import org.mycorp.models.messages.ocpp.CSMSConfirmationClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OCPPMessageHandlerContext {
    private final Map<CSMSConfirmationClassification, OCPPConfirmationHandler> ocppMessageOperatorMap;

    @Autowired
    public OCPPMessageHandlerContext(Map<CSMSConfirmationClassification, OCPPConfirmationHandler> ocppMessageOperatorMap) {
        this.ocppMessageOperatorMap = ocppMessageOperatorMap;
    }
/*
    public OCPPConfirmationHandler getOCPPMessageOperatorImpl(OCPPMessage ocppMessage) throws ClassNotFoundException {
        CSMSConfirmationClassification messageType = getMessageType(ocppMessage);
        return ocppMessageOperatorMap.get(messageType);
    }

    private CSMSConfirmationClassification getMessageType(OCPPMessage ocppMessage) throws ClassNotFoundException {
        for (CSMSConfirmationClassification t : CSMSConfirmationClassification.values()) {
            if (t.getMessageType().isInstance(ocppMessage)) {
                return t;
            }
        }
        throw new ClassNotFoundException("Message type not supported");
    }
    */
}
