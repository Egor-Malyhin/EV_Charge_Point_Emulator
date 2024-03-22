package org.mycorp.models.v2g_messages.types;

import javax.xml.bind.annotation.XmlElement;

public class PhysicalValue {
    @XmlElement(name = "Multiplier", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private int multiplier;
    @XmlElement(name = "Unit", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private String unit;
    @XmlElement(name = "Value", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private int value;

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

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
