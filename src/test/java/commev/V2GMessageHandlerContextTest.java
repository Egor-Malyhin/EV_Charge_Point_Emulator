package commev;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mycorp.commev.messagehandlers.SessionSetupHandler;
import org.mycorp.commev.messagehandlers.V2GMessageHandler;
import org.mycorp.commev.messagehandlers.V2GMessageHandlerContext;
import org.mycorp.main.ApplicationConfiguration;
import org.mycorp.models.messages.v2g.req.SessionSetupReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class V2GMessageHandlerContextTest {
    @Autowired
    private V2GMessageHandlerContext v2GMessageHandlerContext;
    @Mock
    private SessionSetupHandler sessionSetupHandler;

    @Test
    public void getMessageHandlerImplTest() {
        Optional<V2GMessageHandler> messageHandler = v2GMessageHandlerContext.getMessageHandlerImpl(SessionSetupReq.class.getSimpleName());
        Assert.assertEquals(messageHandler.get(), sessionSetupHandler);
    }

    @Test
    public void getMessageHandlerImplExceptionTest() {
        Optional<V2GMessageHandler> messageHandler = v2GMessageHandlerContext.getMessageHandlerImpl("SomeExceptionString");
        Assert.assertThrows(NoSuchElementException.class, messageHandler::get);
    }
}
