package org.mycorp.models.messages.v2g;

import org.mycorp.models.messages.v2g.req.V2GMessageReq;
import org.mycorp.models.messages.v2g.res.V2GMessageRes;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({V2GMessageRes.class, V2GMessageReq.class})
public abstract class V2GBodyAbstractType {
}
