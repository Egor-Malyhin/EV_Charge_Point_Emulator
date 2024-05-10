package org.mycorp.messages.req;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@XmlRootElement(name = "SessionSetupReq", namespace = "urn:iso:15118:2:2013:MsgBody")
public class SessionSetupReq extends V2GMessageReq {
    @XmlElement(name = "EVCCID", namespace = "urn:iso:15118:2:2013:MsgBody")
    private byte[] evccId;

    public SessionSetupReq(byte[] evccId) {
        this.evccId = evccId;
    }
}
