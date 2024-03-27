package org.mycorp.models.v2g_messages.req;

import org.mycorp.models.v2g_messages.types.ChargeProgress;

import javax.xml.bind.annotation.XmlElement;

public class PowerDeliveryReq extends V2GMessageReq{
    @XmlElement(name = "ChargeProgress", namespace = "urn:iso:15118:2:2013:MsgBody")
    private ChargeProgress chargeProgress;

    public PowerDeliveryReq(ChargeProgress chargeProgress) {
        this.chargeProgress = chargeProgress;
    }

    public ChargeProgress getChargeProgress() {
        return chargeProgress;
    }

    public void setChargeProgress(ChargeProgress chargeProgress) {
        this.chargeProgress = chargeProgress;
    }
}
