package org.mycorp.models.messages.v2g.req;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ChargingStatusReq", namespace = "urn:iso:15118:2:2013:MsgBody")
public class ChargingStatusReq extends V2GMessageReq {
    public ChargingStatusReq() {
    }
}