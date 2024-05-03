package org.mycorp.commcsms.messagehandlers;

import eu.chargetime.ocpp.model.Confirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

//
@Component
public class OCPPConfirmationHandlerContext {
    private final Map<String, OCPPConfirmationHandler> ocppMessageOperatorMap;

    @Autowired
    public OCPPConfirmationHandlerContext(@Qualifier("ocppConfirmationHandlerMap") Map<String, OCPPConfirmationHandler> ocppMessageOperatorMap) {
        this.ocppMessageOperatorMap = ocppMessageOperatorMap;
    }

    public Optional<OCPPConfirmationHandler> getOCPPConfirmationHandlerImpl(String confirmationSimpleClassName) {
        return Optional.ofNullable(ocppMessageOperatorMap.get(confirmationSimpleClassName));
    }
}
