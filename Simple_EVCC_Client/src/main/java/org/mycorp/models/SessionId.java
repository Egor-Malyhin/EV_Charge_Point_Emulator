package org.mycorp.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionId {
    private static SessionId instance;
    private byte[] sessionIdValue;

    private SessionId() {
        sessionIdValue = new byte[]{0};
    }

    public static SessionId getInstance() {
        if (instance == null)
            instance = new SessionId();
        return instance;
    }
}
