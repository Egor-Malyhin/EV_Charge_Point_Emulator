package org.mycorp.models.v2g_messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "V2GMessage", namespace = "urn:iso:15118:2:2013:MsgDef")
public class V2GMessage {
    @XmlElement(name = "Header", namespace = "urn:iso:15118:2:2013:MsgDef")
    private V2GHeader header;

    @XmlElement(name = "Body", namespace = "urn:iso:15118:2:2013:MsgDef")
    private V2GBody body;

    public V2GMessage(V2GHeader header, V2GBody body) {
        this.header = header;
        this.body = body;
    }

    public V2GHeader getHeader() {
        return header;
    }

    public V2GBody getBody() {
        return body;
    }

    public void setHeader(V2GHeader header) {
        this.header = header;
    }

    public void setBody(V2GBody body) {
        this.body = body;
    }
}
