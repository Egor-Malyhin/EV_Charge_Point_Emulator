package org.mycorp.csms_communication.message_handlers;

import org.mycorp.models.ocpp_messages.OCPPMessage;
import org.mycorp.models.ocpp_messages.OCPPMessageProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OCPPMessageHandlerContext {
    private final Map<OCPPMessageProfiles, OCPPMessageHandler> ocppMessageOperatorMap;

    @Autowired
    public OCPPMessageHandlerContext(@Qualifier("ocppMessageOperatorMap") Map<OCPPMessageProfiles, OCPPMessageHandler> ocppMessageOperatorMap) {
        this.ocppMessageOperatorMap = ocppMessageOperatorMap;
    }

    public OCPPMessageHandler getOCPPMessageOperatorImpl(OCPPMessage ocppMessage) throws ClassNotFoundException {
        OCPPMessageProfiles messageType = getMessageType(ocppMessage);
        return ocppMessageOperatorMap.get(messageType);
    }

    private OCPPMessageProfiles getMessageType(OCPPMessage ocppMessage) throws ClassNotFoundException {
        for (OCPPMessageProfiles t : OCPPMessageProfiles.values()) {
            if (t.getMessageType().isInstance(ocppMessage)) {
                return t;
            }
        }
        throw new ClassNotFoundException("Message type not supported");
    }
}
