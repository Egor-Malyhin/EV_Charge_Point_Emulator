package org.mycorp.ev_communication;

import com.siemens.ct.exi.core.EXIFactory;
import com.siemens.ct.exi.core.exceptions.EXIException;
import com.siemens.ct.exi.main.api.sax.EXIResult;
import com.siemens.ct.exi.core.helpers.DefaultEXIFactory;
import org.mycorp.models.v2g_messages.V2GMessage;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import java.io.*;
import javax.xml.parsers.SAXParserFactory;

public class XMLConverter {
    private byte[] convertToXML(V2GMessage message){
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            JAXBContext context = JAXBContext.newInstance(message.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(message, baos);

            return baos.toByteArray();
        }catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] convertToEXIMessage(V2GMessage v2GMessage){
        byte[] v2GMessageXML = convertToXML(v2GMessage);
        EXIFactory exiFactory = DefaultEXIFactory.newInstance();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ByteArrayInputStream xmlInputStream = new ByteArrayInputStream(v2GMessageXML)) {

            EXIResult exiResult = new EXIResult(exiFactory);
            exiResult.setOutputStream(baos);

            SAXParser saxParser = factory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(exiResult.getHandler());

            xmlReader.parse(new InputSource(xmlInputStream));

            return baos.toByteArray();
        } catch (IOException | SAXException | EXIException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}

