package org.mycorp.v2g.types;

public enum ResponseCode {
    OK,
    OK_NewSessionEstablished,
    FAILED,
    FAILED_UnknownSession,
    FAILED_PowerDelivery_NotApplied,
    FAILED_ContactorError
}
