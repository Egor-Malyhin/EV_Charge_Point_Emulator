package logging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mycorp.logging.StationEventLogger;
import org.mycorp.main.ApplicationConfiguration;
import org.mycorp.models.events.commcsmshelper.SendBootNotification;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class StationEventLoggerTest {
    @Mock
    private Logger logger;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private StationEventLogger eventLogger;

    @Test
    public void testEventLogging(){
        MockitoAnnotations.initMocks(this);
        publisher.publishEvent(new TestEvent(this));
        publisher.publishEvent(new TestEvent(this));
        publisher.publishEvent(new TestEvent(this));
        publisher.publishEvent(new TestEvent(this));
        publisher.publishEvent(new TestEvent(this));
        publisher.publishEvent(new TestEvent(this));
        publisher.publishEvent(new TestEvent(this));
        publisher.publishEvent(new TestEvent(this));
        publisher.publishEvent(new TestEvent(this));
        //verify(logger).info("Event");
    }

    private static class TestEvent extends ApplicationEvent {
        public TestEvent(Object source) {
            super(source);
        }
    }
}
