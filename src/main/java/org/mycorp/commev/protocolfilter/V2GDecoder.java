package org.mycorp.commev.protocolfilter;

import com.siemens.ct.exi.core.exceptions.EXIException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.mycorp.commev.messagefactory.V2GMessageResFactory;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

@Component
public class V2GDecoder extends ProtocolDecoderAdapter {
    private final XMLConverter xmlConverter;

    @Autowired
    public V2GDecoder(XMLConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }

    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) {
        try {
            byte[] inputMessage = new byte[in.remaining()];
            in.get(inputMessage);
            out.write(xmlConverter.convertToObject(inputMessage));
        } catch (EXIException | TransformerException | JAXBException e) {
            session.write(V2GMessageResFactory.createSessionSetupResMessage(ResponseCode.FAILED, null, 0));
            throw new RuntimeException("Cannot decode client message", e);
        }
    }
}
