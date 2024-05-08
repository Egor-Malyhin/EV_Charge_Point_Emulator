package org.mycorp.messages.types;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class MeterInfo {
    @XmlElement(name = "MeterId", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private int meterId;
    @XmlElement(name = "MeterReading", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private long meterReading;

    public MeterInfo() {
    }

    public MeterInfo(int meterId, long meterReading) {
        this.meterId = meterId;
        this.meterReading = meterReading;
    }
}
