package org.mycorp.v2g.res;

import org.mycorp.v2g.types.ResponseCode;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SessionStopRes", namespace = "urn:iso:15118:2:2013:MsgBody")
public class SessionStopRes extends V2GMessageRes {
    public SessionStopRes(ResponseCode responseCode) {
        super(responseCode);
    }

    public SessionStopRes() {
    }
}
