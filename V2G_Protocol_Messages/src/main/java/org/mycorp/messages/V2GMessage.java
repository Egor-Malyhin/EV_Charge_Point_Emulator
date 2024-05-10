package org.mycorp.messages;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@XmlRootElement(name = "V2GMessage", namespace = "urn:iso:15118:2:2013:MsgDef")
public class V2GMessage {
    @XmlElement(name = "Header", namespace = "urn:iso:15118:2:2013:MsgDef")
    private V2GHeader header;

    @XmlElement(name = "Body", namespace = "urn:iso:15118:2:2013:MsgDef")
    private V2GBody body;
}
