package org.mycorp.messages.res;

import lombok.Getter;
import org.mycorp.messages.types.AC_EVSEStatus;
import org.mycorp.messages.types.enums.ResponseCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.Objects;

@Getter
@XmlRootElement(name = "PowerDeliveryRes", namespace = "urn:iso:15118:2:2013:MsgBody")
@XmlSeeAlso({ChargingStatusRes.class})
public class PowerDeliveryRes extends V2GMessageRes {
    @XmlElement(name = "AC_EVSEStatus", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private AC_EVSEStatus ac_evseStatus;

    public PowerDeliveryRes(ResponseCode responseCode, AC_EVSEStatus ac_evseStatus) {
        super(responseCode);
        this.ac_evseStatus = ac_evseStatus;
    }

    public PowerDeliveryRes() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PowerDeliveryRes that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getAc_evseStatus(), that.getAc_evseStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAc_evseStatus());
    }
}
