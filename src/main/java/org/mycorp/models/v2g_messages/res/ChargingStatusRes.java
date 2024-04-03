package org.mycorp.models.v2g_messages.res;

import org.mycorp.models.v2g_messages.types.AC_EVSEStatus;
import org.mycorp.models.v2g_messages.types.MeterInfo;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(name = "ChargingStatusRes", namespace = "urn:iso:15118:2:2013:MsgBody")
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

    public String getEvseId() {
        return evseId;
    }

    public MeterInfo getMeterInfo() {
        return meterInfo;
    }

    public void setEvseId(String evseId) {
        this.evseId = evseId;
    }

    public void setMeterInfo(MeterInfo meterInfo) {
        this.meterInfo = meterInfo;
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
