package org.mycorp.commev.messagehandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class V2GMessageHandlerContext {
    private final Map<String, V2GMessageHandler> v2GMessageHandlersMap;

    @Autowired
    public V2GMessageHandlerContext(@Qualifier("v2gMessageHandlersMap") Map<String, V2GMessageHandler> v2GMessageHandlersMap) {
        this.v2GMessageHandlersMap = v2GMessageHandlersMap;
    }

    public Optional<V2GMessageHandler> getMessageHandlerImpl(String messageHandlerSimpleClassName) {
        return Optional.ofNullable(v2GMessageHandlersMap.get(messageHandlerSimpleClassName));
    }
}
