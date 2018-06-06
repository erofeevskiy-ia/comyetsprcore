package configurations;

import app.App;
import app.enums.EventType;
import interfaces.EventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan({"app", "loggers"})
@Import({LoggersConf.class, ClientConf.class, UtilConf.class})
public class AppConf {

    @Resource(name = "consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Resource(name = "combinedEventLogger")
    private EventLogger combinedEventLogger;


    @Bean
    public App app() {

        Map<EventType, EventLogger> eventLoggerMap = new HashMap<>();

        eventLoggerMap.put(EventType.INFO, consoleEventLogger);
        eventLoggerMap.put(EventType.ERROR, combinedEventLogger);

        App app = new App();
        app.setEventLoggerMap(eventLoggerMap);

        return app;
    }
}

