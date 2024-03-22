package org.mycorp.models.v2g_messages.res;

import org.mycorp.models.v2g_messages.types.ResponseCode;

import javax.xml.bind.annotation.XmlElement;

public class SessionSetupRes extends V2GMessageRes {
    @XmlElement(name = "EVSEID", namespace = "urn:iso:15118:2:2013:MsgBody")
    private String evseId;
    @XmlElement(name = "EVSETimeStamp", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private long evseTimeStamp;
    public SessionSetupRes(ResponseCode responseCode, String evseId, long evseTimeStamp) {
        super(responseCode);
        this.evseId=evseId;
        this.evseTimeStamp=evseTimeStamp;
    }

    public String getEvseId() {
        return evseId;
    }

    public long getEvseTimeStamp() {
        return evseTimeStamp;
    }

    public void setEvseId(String evseId) {
        this.evseId = evseId;
    }

    public void setEvseTimeStamp(long evseTimeStamp) {
        this.evseTimeStamp = evseTimeStamp;
    }
}
