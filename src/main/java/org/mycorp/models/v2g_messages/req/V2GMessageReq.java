package org.mycorp.models.v2g_messages.req;

import org.mycorp.models.v2g_messages.V2GBodyAbstractType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ChargeParameterDiscoveryReq.class, ChargingStatusReq.class, PowerDeliveryReq.class, SessionSetupReq.class, SessionStopReq.class})
public abstract class V2GMessageReq extends V2GBodyAbstractType {

}
