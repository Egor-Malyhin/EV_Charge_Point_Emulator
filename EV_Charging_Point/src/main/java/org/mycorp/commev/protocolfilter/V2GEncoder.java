package org.mycorp.commev.protocolfilter;

import com.siemens.ct.exi.core.exceptions.EXIException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.mycorp.XMLConverter;
import org.mycorp.messages.V2GMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

@Component
public class V2GEncoder extends ProtocolEncoderAdapter {
    private final XMLConverter xmlConverter;

    @Autowired
    public V2GEncoder() {
        this.xmlConverter = new XMLConverter();
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) {
        try {
            V2GMessage messageObject = (V2GMessage) message;
            byte[] byteArrayMessage = xmlConverter.convertToEXIMessage(messageObject);
            out.write(IoBuffer.wrap(byteArrayMessage));
        } catch (SAXException | EXIException | ParserConfigurationException | JAXBException e) {
            throw new RuntimeException("Cannot convert object message to EXI", e);
        }
    }
}
