import org.junit.Test;
import org.mycorp.ev_communication.XMLConverter;
import org.mycorp.models.v2g_messages.V2GBody;
import org.mycorp.models.v2g_messages.V2GHeader;
import org.mycorp.models.v2g_messages.V2GMessage;
import org.mycorp.models.v2g_messages.V2GSessionIdCounter;
import org.mycorp.models.v2g_messages.res.PowerDeliveryRes;
import org.mycorp.models.v2g_messages.res.SessionSetupRes;
import org.mycorp.models.v2g_messages.types.AC_EVSEStatus;
import org.mycorp.models.v2g_messages.types.EVSENotification;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import javax.xml.bind.JAXBException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class XmlConverterTest {

    private static final XMLConverter xmlConverter;

    static {
        try {
            xmlConverter = new XMLConverter();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void convertV2GToXmlTest() throws Exception {
        PowerDeliveryRes powerDeliveryRes = new PowerDeliveryRes(ResponseCode.OK, new AC_EVSEStatus(true, 5, EVSENotification.NONE));
        V2GMessage v2GMessage = new V2GMessage(new V2GHeader(V2GSessionIdCounter.getInstance().getSessionId()), new V2GBody(powerDeliveryRes));

        Method method = xmlConverter.getClass().getDeclaredMethod("convertToXML", V2GMessage.class);
        method.setAccessible(true);
        Object convertedMessage = method.invoke(xmlConverter, v2GMessage);

        String messageString = new String((byte[]) convertedMessage);

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
    public void convertV2GToExiTest() {
        SessionSetupRes sessionSetupRes = new SessionSetupRes(ResponseCode.OK, "1", 3);
        V2GMessage v2GMessage = new V2GMessage(new V2GHeader(new byte[]{0x01}), new V2GBody(sessionSetupRes));

        byte[] convertedMessage = xmlConverter.convertToEXIMessage(v2GMessage);
        V2GMessage decodedMessage = xmlConverter.convertToObject(convertedMessage);

        assertEquals(v2GMessage, decodedMessage);
    }
}
