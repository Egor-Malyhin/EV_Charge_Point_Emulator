package org.mycorp.models.messages.v2g.res;

import org.mycorp.models.messages.v2g.types.ResponseCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "SessionSetupRes", namespace = "urn:iso:15118:2:2013:MsgBody")
public class SessionSetupRes extends V2GMessageRes {
    @XmlElement(name = "EVSEID", namespace = "urn:iso:15118:2:2013:MsgBody")
    private String evseId;
    @XmlElement(name = "EVSETimeStamp", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private long evseTimeStamp;

    public SessionSetupRes(ResponseCode responseCode, String evseId, long evseTimeStamp) {
        super(responseCode);
        this.evseId = evseId;
        this.evseTimeStamp = evseTimeStamp;
    }

    public SessionSetupRes() {
    }

    public String getEvseId() {
        return evseId;
    }

    public long getEvseTimeStamp() {
        return evseTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionSetupRes that)) return false;
        if (!super.equals(o)) return false;
        return getEvseTimeStamp() == that.getEvseTimeStamp() && Objects.equals(getEvseId(), that.getEvseId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEvseId(), getEvseTimeStamp());
    }
}
