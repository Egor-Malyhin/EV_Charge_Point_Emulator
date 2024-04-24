package org.mycorp.models.messages.v2g.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AC_EVSEChargeParameter {
    @XmlElement(name = "AC_EVSEStatus", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private final AC_EVSEStatus ac_evseStatus;
    @XmlElement(name = "EVSENominalVoltage", namespace = "urn:iso:15118:2:2013:MsgDataTypes", type = PhysicalValue.class)
    private final PhysicalValue evseNominalVoltage;
    @XmlElement(name = "EVSEMaxCurrent", namespace = "urn:iso:15118:2:2013:MsgDataTypes", type = PhysicalValue.class)
    private final PhysicalValue evseMaxCurrent;

    public AC_EVSEChargeParameter(AC_EVSEStatus ac_evseStatus, PhysicalValue evseNominalVoltage, PhysicalValue evseMaxCurrent) {
        this.ac_evseStatus = ac_evseStatus;
        this.evseNominalVoltage = evseNominalVoltage;
        this.evseMaxCurrent = evseMaxCurrent;
    }

    public AC_EVSEStatus getAc_evseStatus() {
        return ac_evseStatus;
    }

    public PhysicalValue getEvseNominalVoltage() {
        return evseNominalVoltage;
    }

    public PhysicalValue getEvseMaxCurrent() {
        return evseMaxCurrent;
    }
}
