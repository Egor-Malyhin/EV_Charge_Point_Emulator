package org.mycorp.messages.req;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mycorp.messages.types.enums.ChargingSession;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@XmlRootElement(name = "SessionStopHandler", namespace = "urn:iso:15118:2:2013:MsgBody")
public class SessionStopReq extends V2GMessageReq {
    @XmlElement(name = "ChargingSession", namespace = "urn:iso:15118:2:2013:MsgBody")
    private ChargingSession chargingSession;

    public SessionStopReq(ChargingSession chargingSession) {
        this.chargingSession = chargingSession;
    }
}
