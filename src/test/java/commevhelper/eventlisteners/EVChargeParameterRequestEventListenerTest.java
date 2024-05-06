package commevhelper.eventlisteners;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mycorp.commevhelper.EVCommunicationBlockHelperInterface;
import org.mycorp.commevhelper.eventlisteners.EVChargeParameterRequestEventListener;
import org.mycorp.main.ApplicationConfiguration;
import org.mycorp.models.Charge;
import org.mycorp.models.events.commev.EVChargeParameterRequest;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class EVChargeParameterRequestEventListenerTest {
    @Mock
    EVCommunicationBlockHelperInterface evCommunicationBlockHelperInterface;

    @InjectMocks
    EVChargeParameterRequestEventListener evChargeParameterRequestEventListener;

    @Test
    public void listenEventTest(){
        evChargeParameterRequestEventListener.listenEvent(new EVChargeParameterRequest(this, new Charge("kwh", 5)));
        Mockito.verify(evCommunicationBlockHelperInterface, Mockito.times(1)).presentChargeParameter();
    }
}
