package commev;

import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mycorp.commev.protocolfilter.V2GDecoder;
import org.mycorp.main.ApplicationConfiguration;
import org.mycorp.models.messages.v2g.V2GBody;
import org.mycorp.models.messages.v2g.V2GHeader;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.mycorp.models.messages.v2g.V2GSessionIdCounter;
import org.mycorp.models.messages.v2g.res.PowerDeliveryRes;
import org.mycorp.models.messages.v2g.types.AC_EVSEStatus;
import org.mycorp.models.messages.v2g.types.EVSENotification;
import org.mycorp.models.messages.v2g.types.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.apache.mina.core.buffer.IoBuffer;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
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
