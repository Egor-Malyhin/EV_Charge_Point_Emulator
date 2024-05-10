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
public class MeterInfo {
    @XmlElement(name = "MeterId", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private int meterId;
    @XmlElement(name = "MeterReading", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private long meterReading;
}
