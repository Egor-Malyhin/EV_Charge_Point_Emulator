package org.mycorp.v2g.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "SessionSetupReq", namespace = "urn:iso:15118:2:2013:MsgBody")
public class SessionSetupReq extends V2GMessageReq {
    @XmlElement(name = "EVCCID", namespace = "urn:iso:15118:2:2013:MsgBody")
    private byte[] evccId;

    public SessionSetupReq(byte[] evccId) {
        this.evccId = evccId;
    }
    public SessionSetupReq(){}

    public byte[] getEvccId() {
        return evccId;
    }

    public void setEvccId(byte[] evccId) {
        this.evccId = evccId;
    }
}
