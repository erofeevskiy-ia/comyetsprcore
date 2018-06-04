package loggers;

import app.Event;
import interfaces.EventLogger;
import lombok.Data;

import java.util.List;

@Data
public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        loggers.forEach(eventLogger -> eventLogger.logEvent(event));
    }
}
