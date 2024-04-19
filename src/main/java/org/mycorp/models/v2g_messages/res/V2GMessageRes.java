package org.mycorp.models.v2g_messages.res;

import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement
@XmlSeeAlso({ChargeParameterDiscoveryRes.class, PowerDeliveryRes.class, SessionSetupRes.class, SessionStopRes.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class V2GMessageRes extends V2GBodyAbstractType {
    @XmlElement(name = "ResponseCode", namespace = "urn:iso:15118:2:2013:MsgBody")
    private ResponseCode responseCode;

    public V2GMessageRes(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public V2GMessageRes() {
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof V2GMessageRes that)) return false;
        return getResponseCode() == that.getResponseCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResponseCode());
    }
}
