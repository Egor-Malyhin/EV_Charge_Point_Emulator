import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mycorp.ev_communication.EVCommunicationBlock;
import org.mycorp.ev_communication.protocol_filter.V2GDecoder;
import org.mycorp.main.MyConfiguration;
import org.mycorp.models.v2g_messages.V2GBody;
import org.mycorp.models.v2g_messages.V2GHeader;
import org.mycorp.models.v2g_messages.V2GMessage;
import org.mycorp.models.v2g_messages.V2GSessionIdCounter;
import org.mycorp.models.v2g_messages.res.PowerDeliveryRes;
import org.mycorp.models.v2g_messages.types.AC_EVSEStatus;
import org.mycorp.models.v2g_messages.types.EVSENotification;
import org.mycorp.models.v2g_messages.types.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import org.apache.mina.core.buffer.IoBuffer;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfiguration.class)
public class V2GDecoderTest {
    @Mock
    private ProtocolDecoderOutput protocolDecoderOutput;
    @Autowired
    private V2GDecoder v2GDecoder;

    @Test
    public void messageDecodeTest() throws Exception {
        IoBuffer ioBuffer = IoBuffer.allocate(128); // Выделение буфера размером 128 байт (можно выбрать подходящий размер)
        ioBuffer.setAutoExpand(true);

        PowerDeliveryRes powerDeliveryRes = new PowerDeliveryRes(ResponseCode.OK, new AC_EVSEStatus(true, 5, EVSENotification.NONE));
        V2GMessage v2GMessage = new V2GMessage(new V2GHeader(V2GSessionIdCounter.getInstance().getSessionId()), new V2GBody(powerDeliveryRes));

        ioBuffer.putObject(v2GMessage);
        ioBuffer.flip();

        v2GDecoder.decode(null, ioBuffer, protocolDecoderOutput);
    }
}
