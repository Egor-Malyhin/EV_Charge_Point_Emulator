package org.mycorp.messages;

import org.mycorp.messages.req.V2GMessageReq;
import org.mycorp.messages.res.V2GMessageRes;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({V2GMessageRes.class, V2GMessageReq.class})
public abstract class V2GBodyAbstractType {
}
