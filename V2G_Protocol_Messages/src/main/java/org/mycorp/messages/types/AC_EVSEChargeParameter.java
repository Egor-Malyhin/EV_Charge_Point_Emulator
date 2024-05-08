package org.mycorp.messages.types;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class AC_EVSEChargeParameter {
    @XmlElement(name = "AC_EVSEStatus", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private AC_EVSEStatus ac_evseStatus;
    @XmlElement(name = "EVSENominalVoltage", namespace = "urn:iso:15118:2:2013:MsgDataTypes", type = PhysicalValue.class)
    private PhysicalValue evseNominalVoltage;
    @XmlElement(name = "EVSEMaxCurrent", namespace = "urn:iso:15118:2:2013:MsgDataTypes", type = PhysicalValue.class)
    private PhysicalValue evseMaxCurrent;

    public AC_EVSEChargeParameter() {
    }

    public AC_EVSEChargeParameter(AC_EVSEStatus ac_evseStatus, PhysicalValue evseNominalVoltage, PhysicalValue evseMaxCurrent) {
        this.ac_evseStatus = ac_evseStatus;
        this.evseNominalVoltage = evseNominalVoltage;
        this.evseMaxCurrent = evseMaxCurrent;
    }

}
