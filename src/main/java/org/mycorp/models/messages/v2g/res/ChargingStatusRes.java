package org.mycorp.models.messages.v2g.res;

import org.mycorp.models.messages.v2g.types.AC_EVSEStatus;
import org.mycorp.models.messages.v2g.types.MeterInfo;
import org.mycorp.models.messages.v2g.types.ResponseCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

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

    public ChargingStatusRes() {
    }

    public String getEvseId() {
        return evseId;
    }

    public MeterInfo getMeterInfo() {
        return meterInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChargingStatusRes that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getEvseId(), that.getEvseId()) && Objects.equals(getMeterInfo(), that.getMeterInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEvseId(), getMeterInfo());
    }
}
