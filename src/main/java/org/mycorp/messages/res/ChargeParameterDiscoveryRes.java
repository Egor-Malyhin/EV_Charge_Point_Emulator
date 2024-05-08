package org.mycorp.messages.res;

import lombok.Getter;
import org.mycorp.messages.types.AC_EVSEChargeParameter;
import org.mycorp.messages.types.enums.EVSEProcessing;
import org.mycorp.messages.types.enums.ResponseCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Getter
@XmlRootElement(name = "ChargeParameterDiscoveryRes", namespace = "urn:iso:15118:2:2013:MsgBody")
public class ChargeParameterDiscoveryRes extends V2GMessageRes {
    @XmlElement(name = "EVSEProcessing", namespace = "urn:iso:15118:2:2013:MsgBody")
    private EVSEProcessing evseProcessing;

    @XmlElement(name = "AC_EVSEChargeParameter", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private AC_EVSEChargeParameter ac_evseChargeParameter;

    public ChargeParameterDiscoveryRes(ResponseCode responseCode, AC_EVSEChargeParameter ac_evseChargeParameter, EVSEProcessing evseProcessing) {
        super(responseCode);
        this.ac_evseChargeParameter = ac_evseChargeParameter;
        this.evseProcessing = evseProcessing;
    }

    public ChargeParameterDiscoveryRes() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChargeParameterDiscoveryRes that)) return false;
        if (!super.equals(o)) return false;
        return getEvseProcessing() == that.getEvseProcessing() && Objects.equals(getAc_evseChargeParameter(), that.getAc_evseChargeParameter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEvseProcessing(), getAc_evseChargeParameter());
    }
}
