package app;

import app.enums.EventType;
import configurations.AppConf;
import interfaces.EventLogger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> eventLoggerMap;

    private void logEvent(Event event, String str, EventType eventType) {

        EventLogger logger = eventLoggerMap.get(eventType);
        if (logger == null)
            logger = defaultLogger;
        String message = str.replaceAll(client.getId(), client.getFullName());
        event.setMsg(client.getGreeting() + message);
        logger.logEvent(event);

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();

        applicationContext.register(AppConf.class);

        applicationContext.refresh();

        App app = applicationContext.getBean(App.class);

        applicationContext.registerShutdownHook();

        Event event = applicationContext.getBean(Event.class);
        app.logEvent(event, "I'am 1", EventType.ERROR);

        Event event1 = applicationContext.getBean(Event.class);
        app.logEvent(event1, "I'am 2", EventType.INFO);

        Event event2 = applicationContext.getBean(Event.class);
        app.logEvent(event2, "I'am 3", null);

    }
}

