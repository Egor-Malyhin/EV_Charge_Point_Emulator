package org.mycorp.messages;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import java.util.Arrays;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class V2GHeader {
    @XmlElement(name = "SESSIONID", namespace = "urn:iso:15118:2:2013:MsgHeader")
    private byte[] sessionId;
}
