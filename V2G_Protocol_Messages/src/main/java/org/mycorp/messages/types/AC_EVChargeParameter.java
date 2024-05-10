package org.mycorp.messages.types;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
}
