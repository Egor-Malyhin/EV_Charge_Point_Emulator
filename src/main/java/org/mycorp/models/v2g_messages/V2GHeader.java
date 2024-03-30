package org.mycorp.models.v2g_messages;

import javax.xml.bind.annotation.XmlElement;
import java.util.Arrays;

public class V2GHeader {
    @XmlElement(name = "SESSIONID", namespace = "urn:iso:15118:2:2013:MsgHeader")
    private byte[] sessionId;

    public V2GHeader(byte[] sessionId) {
        this.sessionId = sessionId;
    }

    public V2GHeader(){};

    public byte[] getSessionId() {
        return sessionId;
    }

    public void setSessionId(byte[] sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof V2GHeader v2GHeader)) return false;
        return Arrays.equals(getSessionId(), v2GHeader.getSessionId());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getSessionId());
    }
}
