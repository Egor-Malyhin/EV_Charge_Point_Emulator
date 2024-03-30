package org.mycorp.models.v2g_messages.res;

import org.mycorp.models.v2g_messages.req.PowerDeliveryReq;
import org.mycorp.models.v2g_messages.types.AC_EVSEStatus;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement(name = "PowerDeliveryRes", namespace = "urn:iso:15118:2:2013:MsgBody")
@XmlSeeAlso({ChargingStatusRes.class})
@XmlType(name = "PowerDeliveryRes", namespace = "urn:iso:15118:2:2013:MsgBody")
public class PowerDeliveryRes extends V2GMessageRes {
    @XmlElement(name = "AC_EVSEStatus", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private AC_EVSEStatus ac_evseStatus;
    public PowerDeliveryRes(ResponseCode responseCode, AC_EVSEStatus ac_evseStatus) {
        super(responseCode);
        this.ac_evseStatus = ac_evseStatus;
    }

    public PowerDeliveryRes(){}

    public AC_EVSEStatus getAc_evseStatus() {
        return ac_evseStatus;
    }

    public void setAc_evseStatus(AC_EVSEStatus ac_evseStatus) {
        this.ac_evseStatus = ac_evseStatus;
    }
}
