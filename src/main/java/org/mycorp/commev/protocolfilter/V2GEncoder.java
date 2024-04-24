package org.mycorp.commev.protocolfilter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V2GEncoder extends ProtocolEncoderAdapter {
    private final XMLConverter xmlConverter;

    @Autowired
    public V2GEncoder(XMLConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        V2GMessage messageObject = (V2GMessage) message;
        xmlConverter.convertToEXIMessage(messageObject);
    }
}
