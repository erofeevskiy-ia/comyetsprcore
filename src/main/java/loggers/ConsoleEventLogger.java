package loggers;

import app.Event;
import interfaces.EventLogger;
import org.springframework.stereotype.Component;

@Component(value = "consoleEventLogger")
public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(Event event){
        System.out.println(event);
    }
}
