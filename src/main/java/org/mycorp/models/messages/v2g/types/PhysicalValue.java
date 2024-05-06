package org.mycorp.models.messages.v2g.types;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class PhysicalValue {
    @XmlElement(name = "Multiplier", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private int multiplier;
    @XmlElement(name = "Unit", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private String unit;
    @XmlElement(name = "Value", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private int value;

    public PhysicalValue() {
    }

    public PhysicalValue(int multiplier, String unit, int value) {
        this.multiplier = multiplier;
        this.unit = unit;
        this.value = value;
    }
}
