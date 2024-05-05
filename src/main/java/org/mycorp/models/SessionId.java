package org.mycorp.models;

public class SessionId {
    private static SessionId instance;
    private byte[] sessionIdValue;

    private SessionId(){
        sessionIdValue = new byte[]{0};
    }

    public static SessionId getInstance(){
        if(instance == null)
            instance = new SessionId();
        return instance;
    }


    public byte[] getSessionIdValue() {
        return sessionIdValue;
    }

    public void setSessionIdValue(byte[] sessionIdValue) {
        this.sessionIdValue = sessionIdValue;
    }
}
