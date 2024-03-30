package org.mycorp.models.v2g_messages.res;

import org.mycorp.models.v2g_messages.V2GBodyAbstractType;
import org.mycorp.models.v2g_messages.req.PowerDeliveryReq;
import org.mycorp.models.v2g_messages.req.V2GMessageReq;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlSeeAlso({ChargeParameterDiscoveryRes.class, PowerDeliveryRes.class, SessionSetupRes.class, SessionStopRes.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class V2GMessageRes extends V2GBodyAbstractType {
    @XmlElement(name = "ResponseCode", namespace = "urn:iso:15118:2:2013:MsgBody")
    private ResponseCode responseCode;

    public V2GMessageRes(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public V2GMessageRes(){}

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
