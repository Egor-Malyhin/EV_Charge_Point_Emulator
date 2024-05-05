package org.mycorp.v2g.req;

import org.mycorp.v2g.types.AC_EVChargeParameter;
import org.mycorp.v2g.types.RequestedEnergyTransferMode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ChargeParameterDiscoveryReq", namespace = "urn:iso:15118:2:2013:MsgBody")
public class ChargeParameterDiscoveryReq extends V2GMessageReq {
    @XmlElement(name = "RequestedEnergyTransferMode", namespace = "urn:iso:15118:2:2013:MsgBody")
    private final RequestedEnergyTransferMode requestedEnergyTransferMode;
    @XmlElement(name = "AC_EVChargeParameter", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private final AC_EVChargeParameter acEvChargeParameter;

    public ChargeParameterDiscoveryReq(RequestedEnergyTransferMode requestedEnergyTransferMode, AC_EVChargeParameter acEvChargeParameter) {
        this.requestedEnergyTransferMode = requestedEnergyTransferMode;
        this.acEvChargeParameter = acEvChargeParameter;
    }

    public RequestedEnergyTransferMode getRequestedEnergyTransferMode() {
        return requestedEnergyTransferMode;
    }

    public AC_EVChargeParameter getAcEvChargeParameter() {
        return acEvChargeParameter;
    }
}
