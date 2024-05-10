package org.mycorp.messages.req;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@EqualsAndHashCode
@XmlRootElement(name = "ChargingStatusReq", namespace = "urn:iso:15118:2:2013:MsgBody")
public class ChargingStatusReq extends V2GMessageReq {
}
