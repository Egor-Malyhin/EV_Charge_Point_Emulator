package org.mycorp.models.v2g_messages.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MeterInfo {
    @XmlElement(name = "MeterId", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private final int meterId;
    @XmlElement(name = "MeterReading", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private final long meterReading;

    public MeterInfo(int meterId, long meterReading) {
        this.meterId = meterId;
        this.meterReading = meterReading;
    }

    public int getMeterId() {
        return meterId;
    }

    public long getMeterReading() {
        return meterReading;
    }
}
