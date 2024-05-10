package org.mycorp.messages.res;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mycorp.messages.types.AC_EVSEStatus;
import org.mycorp.messages.types.MeterInfo;
import org.mycorp.messages.types.enums.ResponseCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "ChargingStatusRes", namespace = "urn:iso:15118:2:2013:MsgBody")
public class ChargingStatusRes extends PowerDeliveryRes {
    @XmlElement(name = "EVSEID", namespace = "urn:iso:15118:2:2013:MsgBody")
    private String evseId;
    @XmlElement(name = "MeterInfo", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private MeterInfo meterInfo;

    public ChargingStatusRes(ResponseCode responseCode, AC_EVSEStatus ac_evseStatus, String evseId, MeterInfo meterInfo) {
        super(responseCode, ac_evseStatus);
        this.evseId = evseId;
        this.meterInfo = meterInfo;
    }
}
