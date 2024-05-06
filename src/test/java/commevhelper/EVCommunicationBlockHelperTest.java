package commevhelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mycorp.commevhelper.EVCommunicationBlockHelper;
import org.mycorp.main.ApplicationConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class EVCommunicationBlockHelperTest {
    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    EVCommunicationBlockHelper evCommunicationBlockHelper;

    @Test
    public void presentChargeParameterTest() {
        evCommunicationBlockHelper.presentChargeParameter();
        Mockito.verify(applicationEventPublisher, Mockito.times(1)).publishEvent(any());
    }

    @Test
    public void acceptSessionClose() {
        evCommunicationBlockHelper.acceptSessionClose();
        Mockito.verify(applicationEventPublisher, Mockito.times(1)).publishEvent(any());
    }
}
