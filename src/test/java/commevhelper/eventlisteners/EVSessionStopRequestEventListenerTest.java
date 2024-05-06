package commevhelper.eventlisteners;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mycorp.commevhelper.EVCommunicationBlockHelperInterface;
import org.mycorp.commevhelper.eventlisteners.EVChargeParameterRequestEventListener;
import org.mycorp.commevhelper.eventlisteners.EVSessionStopRequestEventListener;
import org.mycorp.main.ApplicationConfiguration;
import org.mycorp.models.Charge;
import org.mycorp.models.events.commev.EVChargeParameterRequest;
import org.mycorp.models.events.commev.EVSessionStopRequest;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class EVSessionStopRequestEventListenerTest {
    @Mock
    EVCommunicationBlockHelperInterface evCommunicationBlockHelperInterface;

    @InjectMocks
    EVSessionStopRequestEventListener evSessionStopRequestEventListener;

    @Test
    public void listenEventTest(){
        evSessionStopRequestEventListener.listenEvent(new EVSessionStopRequest(this));
        Mockito.verify(evCommunicationBlockHelperInterface, Mockito.times(1)).acceptSessionClose();
    }
}
