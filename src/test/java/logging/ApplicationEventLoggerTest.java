package logging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mycorp.logging.ApplicationEventLogger;
import org.mycorp.main.ApplicationConfiguration;
import org.springframework.context.ApplicationEvent;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ApplicationEventLoggerTest {

    @InjectMocks
    private ApplicationEventLogger applicationEventLogger;

    @Test
    public void testEventLogging() {
        TestEvent event = new TestEvent(this);
        applicationEventLogger.onApplicationEvent(event);
    }

    private static class TestEvent extends ApplicationEvent {
        public TestEvent(Object source) {
            super(source);
        }
    }
}
