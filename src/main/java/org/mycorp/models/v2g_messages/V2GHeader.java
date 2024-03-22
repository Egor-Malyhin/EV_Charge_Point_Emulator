package org.mycorp.models.v2g_messages;

import javax.xml.bind.annotation.XmlElement;

public class V2GHeader {
    @XmlElement(name = "SESSIONID", namespace = "urn:iso:15118:2:2013:MsgHeader")
    private byte[] sessionId;

    public V2GHeader(byte[] sessionId) {
        this.sessionId = sessionId;
    }

    public byte[] getSessionId() {
        return sessionId;
    }

    public void setSessionId(byte[] sessionId) {
        this.sessionId = sessionId;
    }
}
