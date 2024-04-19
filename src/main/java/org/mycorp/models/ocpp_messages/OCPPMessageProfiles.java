package org.mycorp.models.ocpp_messages;

import org.mycorp.models.ocpp_messages.core_profile.OCPPMessageCoreProfile;

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
