package org.mycorp.commcsms.message_handlers;

import eu.chargetime.ocpp.model.Confirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class OCPPConfirmationHandlerContext {
    private final Map<String, OCPPConfirmationHandler> ocppMessageOperatorMap;

    @Autowired
    public OCPPConfirmationHandlerContext(Map<String, OCPPConfirmationHandler> ocppMessageOperatorMap) {
        this.ocppMessageOperatorMap = ocppMessageOperatorMap;
    }

    public Optional<OCPPConfirmationHandler> getOCPPConfirmationHandlerImpl(Confirmation confirmation) {
        return Optional.of(ocppMessageOperatorMap.get(confirmation.getClass().getName()));
    }
}
