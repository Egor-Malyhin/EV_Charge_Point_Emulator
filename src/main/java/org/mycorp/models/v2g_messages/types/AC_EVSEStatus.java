package org.mycorp.models.v2g_messages.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AC_EVSEStatus {
    @XmlElement(name = "RCD", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private boolean RCD;
    @XmlElement(name = "NotificationMaxDelay", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private int notificationMaxDelay;
    @XmlElement(name = "EVSENotification", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    private EVSENotification evseNotification;

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

    public void setRCD(boolean RCD) {
        this.RCD = RCD;
    }

    public void setNotificationMaxDelay(int notificationMaxDelay) {
        this.notificationMaxDelay = notificationMaxDelay;
    }

    public void setEvseNotification(EVSENotification evseNotification) {
        this.evseNotification = evseNotification;
    }
}
