package org.mycorp.models.messages.v2g.req;

import org.mycorp.models.messages.v2g.types.ChargeProgress;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PowerDeliveryReq", namespace = "urn:iso:15118:2:2013:MsgBody")
public class PowerDeliveryReq extends V2GMessageReq {
    @XmlElement(name = "ChargeProgress", namespace = "urn:iso:15118:2:2013:MsgBody")
    private ChargeProgress chargeProgress;

    public PowerDeliveryReq(ChargeProgress chargeProgress) {
        this.chargeProgress = chargeProgress;
    }

    public PowerDeliveryReq() {
    }

    public ChargeProgress getChargeProgress() {
        return chargeProgress;
    }
}
