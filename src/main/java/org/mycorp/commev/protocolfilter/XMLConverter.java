package org.mycorp.commev.protocolfilter;

import com.siemens.ct.exi.core.EXIFactory;
import com.siemens.ct.exi.core.exceptions.EXIException;
import com.siemens.ct.exi.core.helpers.DefaultEXIFactory;
import com.siemens.ct.exi.main.api.sax.EXIResult;
import com.siemens.ct.exi.main.api.sax.EXISource;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class XMLConverter {
    private final EXIFactory exiFactory;
    private final JAXBContext context;

    public XMLConverter() {
        try {
            this.context = JAXBContext.newInstance(V2GMessage.class);
            this.exiFactory = DefaultEXIFactory.newInstance();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private byte[] convertToXML(V2GMessage message) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(message, baos);
            return baos.toByteArray();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public byte[] convertToEXIMessage(V2GMessage v2GMessage) {
        byte[] v2GMessageXML = convertToXML(v2GMessage);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ByteArrayInputStream xmlInputStream = new ByteArrayInputStream(v2GMessageXML)) {

            EXIResult exiResult = new EXIResult(exiFactory);
            exiResult.setOutputStream(baos);

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            SAXParser saxParser = saxParserFactory.newSAXParser();

            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(exiResult.getHandler());

            xmlReader.parse(new InputSource(xmlInputStream));

            return baos.toByteArray();
        } catch (IOException | SAXException | EXIException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] decodeEXI(byte[] exiMessage) throws EXIException, TransformerException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ByteArrayInputStream exiInputStream = new ByteArrayInputStream(exiMessage)) {

            SAXSource exiSource = new EXISource(exiFactory);
            exiSource.setInputSource(new InputSource(exiInputStream));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(exiSource, new StreamResult(baos));
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public V2GMessage convertToObject(byte[] exiMessage) throws JAXBException, EXIException, TransformerException {
        byte[] xmlMessage = decodeEXI(exiMessage);
        try (ByteArrayInputStream xmlInputStream = new ByteArrayInputStream(xmlMessage)) {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (V2GMessage) unmarshaller.unmarshal(xmlInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

