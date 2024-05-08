package org.mycorp.protocolfilter;

import com.siemens.ct.exi.core.exceptions.EXIException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.mycorp.XMLConverter;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

public class V2GDecoder extends ProtocolDecoderAdapter {
    private final XMLConverter xmlConverter;

    public V2GDecoder() {
        this.xmlConverter = new XMLConverter();
    }

    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        try {
            byte[] inputMessage = new byte[in.remaining()];
            in.get(inputMessage);

            out.write(xmlConverter.convertToObject(inputMessage));
        } catch (EXIException | TransformerException | JAXBException e) {
            e.printStackTrace();
            session.write("Wrong message format");
        }
    }
}
