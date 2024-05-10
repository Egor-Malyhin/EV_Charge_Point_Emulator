package org.mycorp.messages.res;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mycorp.messages.types.AC_EVSEStatus;
import org.mycorp.messages.types.enums.ResponseCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.Objects;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "PowerDeliveryRes", namespace = "urn:iso:15118:2:2013:MsgBody")
@XmlSeeAlso({ChargingStatusRes.class})
public class PowerDeliveryRes extends V2GMessageRes {
    @XmlElement(name = "AC_EVSEStatus", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private AC_EVSEStatus ac_evseStatus;

    public PowerDeliveryRes(ResponseCode responseCode, AC_EVSEStatus ac_evseStatus) {
        super(responseCode);
        this.ac_evseStatus = ac_evseStatus;
    }
}
