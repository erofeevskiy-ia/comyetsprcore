package loggers;

import app.Event;
import interfaces.EventLogger;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Data
@Component(value = "combinedEventLogger")
public class CombinedEventLogger implements EventLogger {
    @Resource
    private List<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        loggers.forEach(eventLogger -> eventLogger.logEvent(event));
    }
}
