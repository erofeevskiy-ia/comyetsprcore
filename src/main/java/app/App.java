package app;

import app.enums.EventType;
import interfaces.EventLogger;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

@Data
@AllArgsConstructor
public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> eventLoggerMap;

    private void logEvent(Event event, String str, EventType eventType) {

        EventLogger logger = eventLoggerMap.get(eventType);
        if (logger == null)
            logger = defaultLogger;
        logger.logEvent(event);
        String message = str.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        defaultLogger.logEvent(event);

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        App app = applicationContext.getBean(App.class);
        int count = 0;
        applicationContext.registerShutdownHook();
        Event event = applicationContext.getBean(Event.class);
        app.logEvent(event, "I'am 1", EventType.ERROR);

        Event event1 = applicationContext.getBean(Event.class);
        app.logEvent(event1, "I'am 2", EventType.INFO);

        Event event2 = applicationContext.getBean(Event.class);
        app.logEvent(event2, "I'am 3", null);
    }
}

