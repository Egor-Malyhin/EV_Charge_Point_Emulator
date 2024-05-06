package org.mycorp.models.messages.v2g.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AC_EVChargeParameter {
    @XmlElement(name = "EАmount", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private PhysicalValue eАmount;
    @XmlElement(name = "EVMaxVoltage", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private PhysicalValue evMaxVoltage;
    @XmlElement(name = "EVMaxCurrent", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private PhysicalValue evMaxCurrent;
    @XmlElement(name = "EVMinCurrent", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private PhysicalValue evMinCurrent;

    public AC_EVChargeParameter() {
    }
    public AC_EVChargeParameter(PhysicalValue eАmount, PhysicalValue evMaxVoltage, PhysicalValue evMaxCurrent, PhysicalValue evMinCurrent) {
        this.eАmount = eАmount;
        this.evMaxVoltage = evMaxVoltage;
        this.evMaxCurrent = evMaxCurrent;
        this.evMinCurrent = evMinCurrent;
    }

    public PhysicalValue geteАmount() {
        return eАmount;
    }

    public PhysicalValue getEvMaxVoltage() {
        return evMaxVoltage;
    }

    public PhysicalValue getEvMaxCurrent() {
        return evMaxCurrent;
    }

    public PhysicalValue getEvMinCurrent() {
        return evMinCurrent;
    }
}
