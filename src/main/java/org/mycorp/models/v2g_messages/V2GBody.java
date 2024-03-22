package org.mycorp.models.v2g_messages;

import org.mycorp.models.v2g_messages.res.*;

import javax.xml.bind.annotation.*;

public class V2GBody {
    @XmlElementWrapper(name = "")
    @XmlElements({
            @XmlElement(name = "ChargeParameterDiscoveryRes", type = ChargeParameterDiscoveryRes.class, namespace = "urn:iso:15118:2:2013:MsgBody"),
            @XmlElement(name = "SessionSetupRes", type = SessionSetupRes.class, namespace = "urn:iso:15118:2:2013:MsgBody"),
            @XmlElement(name = "SessionStopRes", type = SessionStopRes.class, namespace = "urn:iso:15118:2:2013:MsgBody"),
            @XmlElement(name = "ChargingStatusRes", type = ChargingStatusRes.class, namespace = "urn:iso:15118:2:2013:MsgBody"),
            @XmlElement(name = "PowerDeliveryRes", type = PowerDeliveryRes.class, namespace = "urn:iso:15118:2:2013:MsgBody")
    })
        private V2GBodyAbstractType v2GBodyAbstractType;

    public V2GBody(V2GBodyAbstractType v2GBodyAbstractType) {
        this.v2GBodyAbstractType = v2GBodyAbstractType;
    }

    public V2GBodyAbstractType getV2GBodyAbstractType() {
        return v2GBodyAbstractType;
    }

    public void setV2GBodyAbstractType(V2GBodyAbstractType v2GBodyAbstractType) {
        this.v2GBodyAbstractType = v2GBodyAbstractType;
    }
}
