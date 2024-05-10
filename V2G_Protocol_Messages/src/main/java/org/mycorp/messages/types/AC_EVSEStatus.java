package org.mycorp.messages.types;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mycorp.messages.types.enums.EVSENotification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@XmlAccessorType(XmlAccessType.FIELD)
public class AC_EVSEStatus {
    @XmlElement(name = "RCD", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private boolean RCD;
    @XmlElement(name = "NotificationMaxDelay", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private int notificationMaxDelay;
    @XmlElement(name = "EVSENotification", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private EVSENotification evseNotification;
}
