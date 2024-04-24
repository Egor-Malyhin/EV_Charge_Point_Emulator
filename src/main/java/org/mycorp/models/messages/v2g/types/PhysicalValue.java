package org.mycorp.models.messages.v2g.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PhysicalValue {
    @XmlElement(name = "Multiplier", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private final int multiplier;
    @XmlElement(name = "Unit", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private final String unit;
    @XmlElement(name = "Value", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private final int value;

    public PhysicalValue(int multiplier, String unit, int value) {
        this.multiplier = multiplier;
        this.unit = unit;
        this.value = value;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public String getUnit() {
        return unit;
    }

    public int getValue() {
        return value;
    }
}
