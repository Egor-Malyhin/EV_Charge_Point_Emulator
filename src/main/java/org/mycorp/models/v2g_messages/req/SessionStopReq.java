package org.mycorp.models.v2g_messages.req;

import org.mycorp.models.v2g_messages.types.ChargingSession;

import javax.xml.bind.annotation.XmlElement;

public class SessionStopReq extends V2GMessageReq{
    @XmlElement(name = "ChargingSession", namespace = "urn:iso:15118:2:2013:MsgBody")
    private ChargingSession chargingSession;

    public SessionStopReq(ChargingSession chargingSession) {
        this.chargingSession = chargingSession;
    }

    public ChargingSession getChargingSession() {
        return chargingSession;
    }

    public void setChargingSession(ChargingSession chargingSession) {
        this.chargingSession = chargingSession;
    }
}
