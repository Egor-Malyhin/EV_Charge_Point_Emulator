package logging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mycorp.logging.StationEventLogger;
import org.mycorp.main.ApplicationConfiguration;
import org.mycorp.models.events.StationEvent;
import org.springframework.test.context.ContextConfiguration;

public class StationEventLoggerTest {

    private StationEventLogger stationEventLogger;

    @Test
    public void testEventLogging() {
        TestEvent event = new TestEvent(this);
        stationEventLogger.onApplicationEvent(event);

    }

    private static class TestEvent extends StationEvent {
        public TestEvent(Object source) {
            super(source);
        }
    }
}
