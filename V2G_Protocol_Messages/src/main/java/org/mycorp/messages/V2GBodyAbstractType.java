package org.mycorp.messages;

import lombok.NoArgsConstructor;
import org.mycorp.messages.req.V2GMessageReq;
import org.mycorp.messages.res.V2GMessageRes;

import javax.xml.bind.annotation.XmlSeeAlso;

@NoArgsConstructor
@XmlSeeAlso({V2GMessageRes.class, V2GMessageReq.class})
public abstract class V2GBodyAbstractType {
}
