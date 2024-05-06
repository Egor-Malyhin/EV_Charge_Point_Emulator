package commev;

import com.siemens.ct.exi.core.exceptions.EXIException;
import org.junit.Test;
import org.mycorp.commev.messagefactory.V2GMessageResFactory;
import org.mycorp.commev.protocolfilter.XMLConverter;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.messages.v2g.V2GBody;
import org.mycorp.models.messages.v2g.V2GHeader;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.mycorp.models.messages.v2g.res.SessionSetupRes;
import org.mycorp.models.messages.v2g.types.enums.EVSENotification;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class XmlConverterTest {

    private final XMLConverter xmlConverter = new XMLConverter();

    private Method convertToXMLMethodInvoke() throws NoSuchMethodException {
        Method method = xmlConverter.getClass().getDeclaredMethod("convertToXML", V2GMessage.class);
        method.setAccessible(true);
        return method;
    }

    private Object usingInvokedConvertToXML(V2GMessage v2GMessage) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return convertToXMLMethodInvoke().invoke(xmlConverter, v2GMessage);
    }

    @Test
    public void convertV2GToXmlTestCasePowerDeliveryRes() throws Exception {
        V2GMessage powerDeliveryResMessage = V2GMessageResFactory.createPowerDeliveryRes(ResponseCode.OK, EVSENotification.NONE);

        byte[] convertedMessage = (byte[]) usingInvokedConvertToXML(powerDeliveryResMessage);
        String messageString = new String((convertedMessage));
        System.out.println(messageString);

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<v2gci_d:V2GMessage xmlns:v2gci_h=\"urn:iso:15118:2:2013:MsgHeader\" xmlns:v2gci_t=\"urn:iso:15118:2:2013:MsgDataTypes\" xmlns:v2gci_b=\"urn:iso:15118:2:2013:MsgBody\" xmlns:v2gci_d=\"urn:iso:15118:2:2013:MsgDef\">\n" +
                "    <v2gci_d:Header>\n" +
                "        <v2gci_h:SESSIONID>AA==</v2gci_h:SESSIONID>\n" +
                "    </v2gci_d:Header>\n" +
                "    <v2gci_d:Body>\n" +
                "        <v2gci_b:PowerDeliveryRes>\n" +
                "            <v2gci_b:ResponseCode>OK</v2gci_b:ResponseCode>\n" +
                "            <v2gci_t:AC_EVSEStatus>\n" +
                "                <v2gci_t:RCD>true</v2gci_t:RCD>\n" +
                "                <v2gci_t:NotificationMaxDelay>5</v2gci_t:NotificationMaxDelay>\n" +
                "                <v2gci_t:EVSENotification>NONE</v2gci_t:EVSENotification>\n" +
                "            </v2gci_t:AC_EVSEStatus>\n" +
                "        </v2gci_b:PowerDeliveryRes>\n" +
                "    </v2gci_d:Body>\n" +
                "</v2gci_d:V2GMessage>\n", messageString);
    }

    @Test
    public void convertV2GToXmlTestCaseSessionSetupRes() throws Exception {
        long timestamp = Instant.now().toEpochMilli();
        V2GMessage sessionSetupResMessage = V2GMessageResFactory.createSessionSetupResMessage(ResponseCode.OK_NewSessionEstablished, StationCharacteristics.evseId, timestamp);

        byte[] convertedMessage = (byte[]) usingInvokedConvertToXML(sessionSetupResMessage);
        String messageString = new String((convertedMessage));
        System.out.println(messageString);

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<v2gci_d:V2GMessage xmlns:v2gci_h=\"urn:iso:15118:2:2013:MsgHeader\" xmlns:v2gci_t=\"urn:iso:15118:2:2013:MsgDataTypes\" xmlns:v2gci_b=\"urn:iso:15118:2:2013:MsgBody\" xmlns:v2gci_d=\"urn:iso:15118:2:2013:MsgDef\">\n" +
                "    <v2gci_d:Header>\n" +
                "        <v2gci_h:SESSIONID>AA==</v2gci_h:SESSIONID>\n" +
                "    </v2gci_d:Header>\n" +
                "    <v2gci_d:Body>\n" +
                "        <v2gci_b:SessionSetupRes>\n" +
                "            <v2gci_b:ResponseCode>OK_NewSessionEstablished</v2gci_b:ResponseCode>\n" +
                "            <v2gci_b:EVSEID>1</v2gci_b:EVSEID>\n" +
                "            <v2gci_t:EVSETimeStamp>" + timestamp + "</v2gci_t:EVSETimeStamp>\n" +
                "        </v2gci_b:SessionSetupRes>\n" +
                "    </v2gci_d:Body>\n" +
                "</v2gci_d:V2GMessage>\n", messageString);
    }

    @Test
    public void convertV2GToXmlTestCaseSessionStopRes() throws Exception {
        V2GMessage sessionStopResMessage = V2GMessageResFactory.createSessionStopResMessage(ResponseCode.OK);

        byte[] convertedMessage = (byte[]) usingInvokedConvertToXML(sessionStopResMessage);
        String messageString = new String((convertedMessage));
        System.out.println(messageString);

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<v2gci_d:V2GMessage xmlns:v2gci_h=\"urn:iso:15118:2:2013:MsgHeader\" xmlns:v2gci_t=\"urn:iso:15118:2:2013:MsgDataTypes\" xmlns:v2gci_b=\"urn:iso:15118:2:2013:MsgBody\" xmlns:v2gci_d=\"urn:iso:15118:2:2013:MsgDef\">\n" +
                "    <v2gci_d:Header>\n" +
                "        <v2gci_h:SESSIONID>AA==</v2gci_h:SESSIONID>\n" +
                "    </v2gci_d:Header>\n" +
                "    <v2gci_d:Body>\n" +
                "        <v2gci_b:SessionStopRes>\n" +
                "            <v2gci_b:ResponseCode>OK</v2gci_b:ResponseCode>\n" +
                "        </v2gci_b:SessionStopRes>\n" +
                "    </v2gci_d:Body>\n" +
                "</v2gci_d:V2GMessage>\n", messageString);
    }

    @Test
    public void convertV2GToXmlTestCaseChargeParameterDiscoveryRes() throws Exception {
        V2GMessage chargeParameterResMessage = V2GMessageResFactory.createChargeParameterDiscoveryResMessage(ResponseCode.OK, StationCharacteristics.ratedVoltage, StationCharacteristics.maxCurrent);

        byte[] convertedMessage = (byte[]) usingInvokedConvertToXML(chargeParameterResMessage);
        String messageString = new String((convertedMessage));
        System.out.println(messageString);

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<v2gci_d:V2GMessage xmlns:v2gci_h=\"urn:iso:15118:2:2013:MsgHeader\" xmlns:v2gci_t=\"urn:iso:15118:2:2013:MsgDataTypes\" xmlns:v2gci_b=\"urn:iso:15118:2:2013:MsgBody\" xmlns:v2gci_d=\"urn:iso:15118:2:2013:MsgDef\">\n" +
                "    <v2gci_d:Header>\n" +
                "        <v2gci_h:SESSIONID>AA==</v2gci_h:SESSIONID>\n" +
                "    </v2gci_d:Header>\n" +
                "    <v2gci_d:Body>\n" +
                "        <v2gci_b:ChargeParameterDiscoveryRes>\n" +
                "            <v2gci_b:ResponseCode>OK</v2gci_b:ResponseCode>\n" +
                "            <v2gci_b:EVSEProcessing>FINISHED</v2gci_b:EVSEProcessing>\n" +
                "            <v2gci_t:AC_EVSEChargeParameter>\n" +
                "                <v2gci_t:AC_EVSEStatus>\n" +
                "                    <v2gci_t:RCD>true</v2gci_t:RCD>\n" +
                "                    <v2gci_t:NotificationMaxDelay>5</v2gci_t:NotificationMaxDelay>\n" +
                "                    <v2gci_t:EVSENotification>NONE</v2gci_t:EVSENotification>\n" +
                "                </v2gci_t:AC_EVSEStatus>\n" +
                "                <v2gci_t:EVSENominalVoltage>\n" +
                "                    <v2gci_t:Multiplier>1</v2gci_t:Multiplier>\n" +
                "                    <v2gci_t:Unit>V</v2gci_t:Unit>\n" +
                "                    <v2gci_t:Value>230</v2gci_t:Value>\n" +
                "                </v2gci_t:EVSENominalVoltage>\n" +
                "                <v2gci_t:EVSEMaxCurrent>\n" +
                "                    <v2gci_t:Multiplier>1</v2gci_t:Multiplier>\n" +
                "                    <v2gci_t:Unit>A</v2gci_t:Unit>\n" +
                "                    <v2gci_t:Value>38</v2gci_t:Value>\n" +
                "                </v2gci_t:EVSEMaxCurrent>\n" +
                "            </v2gci_t:AC_EVSEChargeParameter>\n" +
                "        </v2gci_b:ChargeParameterDiscoveryRes>\n" +
                "    </v2gci_d:Body>\n" +
                "</v2gci_d:V2GMessage>\n", messageString);
    }

    @Test
    public void convertV2GToXmlTestCaseChargingStatusRes() throws Exception {
        V2GMessage chargingStatusResMessage = V2GMessageResFactory.createChargingStatusRes(ResponseCode.OK, EVSENotification.NONE, StationCharacteristics.evseId, 100, StationCharacteristics.meterId );

        byte[] convertedMessage = (byte[]) usingInvokedConvertToXML(chargingStatusResMessage);
        String messageString = new String((convertedMessage));
        System.out.println(messageString);

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<v2gci_d:V2GMessage xmlns:v2gci_h=\"urn:iso:15118:2:2013:MsgHeader\" xmlns:v2gci_t=\"urn:iso:15118:2:2013:MsgDataTypes\" xmlns:v2gci_b=\"urn:iso:15118:2:2013:MsgBody\" xmlns:v2gci_d=\"urn:iso:15118:2:2013:MsgDef\">\n" +
                "    <v2gci_d:Header>\n" +
                "        <v2gci_h:SESSIONID>AA==</v2gci_h:SESSIONID>\n" +
                "    </v2gci_d:Header>\n" +
                "    <v2gci_d:Body>\n" +
                "        <v2gci_b:ChargingStatusRes>\n" +
                "            <v2gci_b:ResponseCode>OK</v2gci_b:ResponseCode>\n" +
                "            <v2gci_t:AC_EVSEStatus>\n" +
                "                <v2gci_t:RCD>true</v2gci_t:RCD>\n" +
                "                <v2gci_t:NotificationMaxDelay>5</v2gci_t:NotificationMaxDelay>\n" +
                "                <v2gci_t:EVSENotification>NONE</v2gci_t:EVSENotification>\n" +
                "            </v2gci_t:AC_EVSEStatus>\n" +
                "            <v2gci_b:EVSEID>1</v2gci_b:EVSEID>\n" +
                "            <v2gci_t:MeterInfo>\n" +
                "                <v2gci_t:MeterId>1</v2gci_t:MeterId>\n" +
                "                <v2gci_t:MeterReading>100</v2gci_t:MeterReading>\n" +
                "            </v2gci_t:MeterInfo>\n" +
                "        </v2gci_b:ChargingStatusRes>\n" +
                "    </v2gci_d:Body>\n" +
                "</v2gci_d:V2GMessage>\n", messageString);
    }

    /*
    @Test
    public void convertV2GToXmlTestCaseException() throws Exception {
        @XmlRootElement
        class SomeExceptionV2GMessage extends V2GMessage {
            private String ee = "ee";
        }
        SomeExceptionV2GMessage someExceptionV2GMessage = new SomeExceptionV2GMessage();

        byte[] convertedMessage = (byte[]) usingInvokedConvertToXML(someExceptionV2GMessage);
        String messageString = new String((convertedMessage));
        System.out.println(messageString);
        assertThrows(JAXBException.class, () -> usingInvokedConvertToXML(someExceptionV2GMessage));
    } */

    @Test
    public void convertV2GToExiTest() throws JAXBException, EXIException, TransformerException, ParserConfigurationException, SAXException {
        SessionSetupRes sessionSetupRes = new SessionSetupRes(ResponseCode.OK, "1", 3);
        V2GMessage v2GMessage = new V2GMessage(new V2GHeader(new byte[]{0x01}), new V2GBody(sessionSetupRes));

        byte[] convertedMessage = xmlConverter.convertToEXIMessage(v2GMessage);
        V2GMessage decodedMessage = xmlConverter.convertToObject(convertedMessage);

        assertEquals(v2GMessage, decodedMessage);
    }
}
