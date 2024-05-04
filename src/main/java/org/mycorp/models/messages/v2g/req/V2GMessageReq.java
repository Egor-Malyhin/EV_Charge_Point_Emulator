package org.mycorp.models.messages.v2g.req;

import org.mycorp.models.messages.v2g.V2GBodyAbstractType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ChargeParameterDiscoveryReq.class, ChargingStatusReq.class, PowerDeliveryReq.class, SessionSetupReq.class, SessionStopReq.class})
public abstract class V2GMessageReq extends V2GBodyAbstractType {
    public V2GMessageReq() {
    }
}
