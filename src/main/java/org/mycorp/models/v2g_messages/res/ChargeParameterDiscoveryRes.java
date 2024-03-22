package org.mycorp.models.v2g_messages.res;

import org.mycorp.models.v2g_messages.types.AC_EVSEChargeParameter;
import org.mycorp.models.v2g_messages.types.EVSEProcessing;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import javax.xml.bind.annotation.XmlElement;

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

    public EVSEProcessing getEvseProcessing() {
        return evseProcessing;
    }

    public AC_EVSEChargeParameter getAc_evseChargeParameter() {
        return ac_evseChargeParameter;
    }

    public void setEvseProcessing(EVSEProcessing evseProcessing) {
        this.evseProcessing = evseProcessing;
    }

    public void setAc_evseChargeParameter(AC_EVSEChargeParameter ac_evseChargeParameter) {
        this.ac_evseChargeParameter = ac_evseChargeParameter;
    }
}
