package org.mycorp.messages.res;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mycorp.messages.V2GBodyAbstractType;
import org.mycorp.messages.types.enums.ResponseCode;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@EqualsAndHashCode
@XmlSeeAlso({ChargeParameterDiscoveryRes.class, PowerDeliveryRes.class, SessionSetupRes.class, SessionStopRes.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class V2GMessageRes extends V2GBodyAbstractType {
    @XmlElement(name = "ResponseCode", namespace = "urn:iso:15118:2:2013:MsgBody")
    private ResponseCode responseCode;
}
