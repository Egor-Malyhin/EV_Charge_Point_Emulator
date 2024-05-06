package logging;

import org.junit.Test;
import org.mycorp.logging.StationEventLogger;
import org.mycorp.models.events.StationEvent;

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
