package org.mycorp.messages.res;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mycorp.messages.types.enums.ResponseCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
}
