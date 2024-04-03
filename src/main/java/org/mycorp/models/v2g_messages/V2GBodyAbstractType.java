package org.mycorp.models.v2g_messages;

import org.mycorp.models.v2g_messages.req.V2GMessageReq;
import org.mycorp.models.v2g_messages.res.V2GMessageRes;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({V2GMessageRes.class, V2GMessageReq.class})
public abstract class V2GBodyAbstractType {
}
