package org.mycorp.models.messages.v2g.types.enums;

public enum ResponseCode {
    OK,
    OK_NewSessionEstablished,
    FAILED,
    FAILED_UnknownSession,
    FAILED_PowerDelivery_NotApplied,
    FAILED_ContactorError
}
