package org.mycorp.messages.req;

import lombok.Getter;
import org.mycorp.messages.types.AC_EVChargeParameter;
import org.mycorp.messages.types.enums.RequestedEnergyTransferMode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "ChargeParameterDiscoveryReq", namespace = "urn:iso:15118:2:2013:MsgBody")
public class ChargeParameterDiscoveryReq extends V2GMessageReq {
    @XmlElement(name = "RequestedEnergyTransferMode", namespace = "urn:iso:15118:2:2013:MsgBody")
    private RequestedEnergyTransferMode requestedEnergyTransferMode;
    @XmlElement(name = "AC_EVChargeParameter", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private AC_EVChargeParameter acEvChargeParameter;

    public ChargeParameterDiscoveryReq(RequestedEnergyTransferMode requestedEnergyTransferMode, AC_EVChargeParameter acEvChargeParameter) {
        this.requestedEnergyTransferMode = requestedEnergyTransferMode;
        this.acEvChargeParameter = acEvChargeParameter;
    }

    public ChargeParameterDiscoveryReq() {
    }

}
