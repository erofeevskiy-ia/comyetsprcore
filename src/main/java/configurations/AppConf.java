package configurations;

import app.App;
import app.Client;
import app.enums.EventType;
import interfaces.EventLogger;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan({"app", "loggers"})
@Import({PropertyConf.class, LoggersConf.class, ClientConf.class, UtilConf.class})
@EnableAspectJAutoProxy
public class AppConf {

    @Resource(name = "consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Resource(name = "combinedEventLogger")
    private EventLogger combinedEventLogger;

    @Autowired
    @Qualifier(value = "cacheFileEventLogger")
    private EventLogger defaultLogger;

    @Autowired
    private Client client;

    @Bean
    public App app() {

        Map<EventType, EventLogger> eventLoggerMap = new HashMap<>();

        eventLoggerMap.put(EventType.INFO, consoleEventLogger);
        eventLoggerMap.put(EventType.ERROR, combinedEventLogger);


        App app = new App();
        app.setDefaultLogger(defaultLogger);
        app.setClient(client);
        app.setEventLoggerMap(eventLoggerMap);

        return app;
    }
}

