package org.mycorp.messages.res;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mycorp.messages.types.AC_EVSEChargeParameter;
import org.mycorp.messages.types.enums.EVSEProcessing;
import org.mycorp.messages.types.enums.ResponseCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "ChargeParameterDiscoveryRes", namespace = "urn:iso:15118:2:2013:MsgBody")
public class ChargeParameterDiscoveryRes extends V2GMessageRes {
    @XmlElement(name = "EVSEProcessing", namespace = "urn:iso:15118:2:2013:MsgBody")
    private EVSEProcessing evseProcessing;
    @XmlElement(name = "AC_EVSEChargeParameter", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private AC_EVSEChargeParameter ac_evseChargeParameter;

    public ChargeParameterDiscoveryRes(ResponseCode responseCode, EVSEProcessing evseProcessing, AC_EVSEChargeParameter ac_evseChargeParameter) {
        super(responseCode);
        this.evseProcessing = evseProcessing;
        this.ac_evseChargeParameter = ac_evseChargeParameter;
    }
}
