package org.mycorp.commev.messagehandlers;

import org.mycorp.models.messages.v2g.V2GBodyAbstractType;
import org.mycorp.models.messages.v2g.V2GMessagesClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class V2GMessageHandlerContext {
    private final Map<V2GMessagesClassification, V2GMessageHandler> v2GMessageHandlersMap;

    @Autowired
    public V2GMessageHandlerContext(@Qualifier("v2GMessageHandlersMap") Map<V2GMessagesClassification, V2GMessageHandler> v2GMessageHandlersMap) {
        this.v2GMessageHandlersMap = v2GMessageHandlersMap;
    }

    public V2GMessageHandler getMessageHandlerImpl(V2GBodyAbstractType v2GBodyAbstractType) throws ClassNotFoundException {
        V2GMessagesClassification messageType = getMessageType(v2GBodyAbstractType);
        return v2GMessageHandlersMap.get(messageType);
    }

    private V2GMessagesClassification getMessageType(V2GBodyAbstractType receivedMessageBodyType) throws ClassNotFoundException {
        for (V2GMessagesClassification t : V2GMessagesClassification.values()) {
            if (t.getMessageType().isInstance(receivedMessageBodyType)) {
                return t;
            }
        }
        throw new ClassNotFoundException();
    }
}
