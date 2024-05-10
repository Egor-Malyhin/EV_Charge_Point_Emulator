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
public class AC_EVSEChargeParameter {
    @XmlElement(name = "AC_EVSEStatus", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private AC_EVSEStatus ac_evseStatus;
    @XmlElement(name = "EVSENominalVoltage", namespace = "urn:iso:15118:2:2013:MsgDataTypes", type = PhysicalValue.class)
    private PhysicalValue evseNominalVoltage;
    @XmlElement(name = "EVSEMaxCurrent", namespace = "urn:iso:15118:2:2013:MsgDataTypes", type = PhysicalValue.class)
    private PhysicalValue evseMaxCurrent;
}
