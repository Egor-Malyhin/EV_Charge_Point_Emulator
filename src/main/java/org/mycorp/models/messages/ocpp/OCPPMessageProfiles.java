package org.mycorp.models.messages.ocpp;

import org.mycorp.models.messages.ocpp.core_profile.OCPPMessageCoreProfile;

public enum OCPPMessageProfiles {
    CORE_PROFILE(OCPPMessageCoreProfile.class);

    private final Class<? extends OCPPMessage> messageType;

    OCPPMessageProfiles(Class<? extends OCPPMessage> messageType) {
        this.messageType = messageType;
    }

    public Class<? extends OCPPMessage> getMessageType() {
        return messageType;
    }
}
