package org.mycorp.messages.res;

import org.mycorp.messages.types.enums.ResponseCode;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SessionStopRes", namespace = "urn:iso:15118:2:2013:MsgBody")
public class SessionStopRes extends V2GMessageRes {
    public SessionStopRes(ResponseCode responseCode) {
        super(responseCode);
    }

    public SessionStopRes() {
    }
}
