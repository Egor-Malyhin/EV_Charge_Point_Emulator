package org.mycorp.messages.types.enums;

public enum ResponseCode {
    OK,
    OK_NewSessionEstablished,
    FAILED,
    FAILED_UnknownSession,
    FAILED_PowerDelivery_NotApplied,
    FAILED_ContactorError
}
