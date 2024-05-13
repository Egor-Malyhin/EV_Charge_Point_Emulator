package logging;

import org.junit.Test;
import org.mycorp.logging.StationEventLogger;
import org.mycorp.models.events.StationEvent;

public class StationEventLoggerTest {

    private final StationEventLogger stationEventLogger = new StationEventLogger();

    @Test
    public void testEventLogging() {
        TestEvent event = new TestEvent(this);
        stationEventLogger.listenEvent(event);
    }

    private static class TestEvent extends StationEvent {
        public TestEvent(Object source) {
            super(source);
        }
    }
}
