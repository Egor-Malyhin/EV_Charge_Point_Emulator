import org.junit.Test;
import org.mycorp.ev_communication.message_builders.MessageBuildersDirector;
import org.mycorp.ev_communication.message_builders.PowerDeliveryResBuilder;
import org.mycorp.models.v2g_messages.V2GBody;
import org.mycorp.models.v2g_messages.V2GHeader;
import org.mycorp.models.v2g_messages.V2GMessage;
import org.mycorp.models.v2g_messages.V2GSessionIdCounter;
import org.mycorp.models.v2g_messages.res.PowerDeliveryRes;
import org.mycorp.models.v2g_messages.types.AC_EVSEStatus;
import org.mycorp.models.v2g_messages.types.EVSENotification;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import static org.junit.Assert.assertEquals;

public class V2GMessageBuildersTest {
    private static MessageBuildersDirector messageBuildersDirector;

    @Test
    public void buildMessageTest() {
        PowerDeliveryRes powerDeliveryRes = new PowerDeliveryRes(ResponseCode.OK, new AC_EVSEStatus(true, 5, EVSENotification.NONE));
        V2GMessage v2GMessage = new V2GMessage(new V2GHeader(V2GSessionIdCounter.getInstance().getSessionId()), new V2GBody(powerDeliveryRes));

        messageBuildersDirector = new MessageBuildersDirector(new PowerDeliveryResBuilder(ResponseCode.OK, EVSENotification.NONE));
        V2GMessage createdMessage = messageBuildersDirector.create();

        assertEquals(v2GMessage, createdMessage);
    }
}
