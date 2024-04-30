package org.mycorp.models.messages.ocpp;


import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.core.*;

public enum CSMSConfirmationClassification {
    AUTHORIZE_CONF(AuthorizeConfirmation.class),
    BOOT_NOTIFICATION_CONF(BootNotificationConfirmation.class),
    START_TRANSACTION_CONF(StartTransactionConfirmation.class),
    STOP_TRANSACTION_CONF(StopTransactionConfirmation.class),
    STATUS_NOTIFICATION_CONF(StatusNotificationConfirmation.class);

    private final Class<? extends Confirmation> confType;

    CSMSConfirmationClassification(Class<? extends Confirmation> confType) {
        this.confType = confType;
    }

    public Class<? extends Confirmation> getConfType() {
        return confType;
    }
}
