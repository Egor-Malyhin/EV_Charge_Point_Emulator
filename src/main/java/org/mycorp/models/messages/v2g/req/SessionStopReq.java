package org.mycorp.models.messages.v2g.req;

import org.mycorp.models.messages.v2g.types.ChargingSession;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SessionStopHandler", namespace = "urn:iso:15118:2:2013:MsgBody")
public class SessionStopReq extends V2GMessageReq {
    @XmlElement(name = "ChargingSession", namespace = "urn:iso:15118:2:2013:MsgBody")
    private ChargingSession chargingSession;

    public SessionStopReq(ChargingSession chargingSession) {
        this.chargingSession = chargingSession;
    }

    public SessionStopReq() {
    }

    public ChargingSession getChargingSession() {
        return chargingSession;
    }

    public void setChargingSession(ChargingSession chargingSession) {
        this.chargingSession = chargingSession;
    }
}
