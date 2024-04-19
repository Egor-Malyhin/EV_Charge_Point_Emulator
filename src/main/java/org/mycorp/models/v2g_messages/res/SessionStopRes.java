package org.mycorp.models.v2g_messages.res;

import org.mycorp.models.v2g_messages.types.ResponseCode;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "SessionStopRes", namespace = "urn:iso:15118:2:2013:MsgBody")
public class SessionStopRes extends V2GMessageRes {
    public SessionStopRes(ResponseCode responseCode) {
        super(responseCode);
    }

    public SessionStopRes(){
    }
}
