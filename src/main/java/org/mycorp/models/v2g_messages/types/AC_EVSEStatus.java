package org.mycorp.models.v2g_messages.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class AC_EVSEStatus {
    @XmlElement(name = "RCD", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private final boolean RCD;
    @XmlElement(name = "NotificationMaxDelay", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private final int notificationMaxDelay;
    @XmlElement(name = "EVSENotification", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private final EVSENotification evseNotification;

    public AC_EVSEStatus(boolean RCD, int notificationMaxDelay, EVSENotification evseNotification) {
        this.RCD = RCD;
        this.notificationMaxDelay = notificationMaxDelay;
        this.evseNotification = evseNotification;
    }

    public boolean isRCD() {
        return RCD;
    }

    public int getNotificationMaxDelay() {
        return notificationMaxDelay;
    }

    public EVSENotification getEvseNotification() {
        return evseNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AC_EVSEStatus that)) return false;
        return isRCD() == that.isRCD() && getNotificationMaxDelay() == that.getNotificationMaxDelay() && getEvseNotification() == that.getEvseNotification();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isRCD(), getNotificationMaxDelay(), getEvseNotification());
    }
}
