package configurations;

import interfaces.EventLogger;
import loggers.CombinedEventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "loggers")
public class LoggersConf {

    @Resource(name = "consoleEventLogger")
    private EventLogger consoleEventLogger;
    @Resource(name = "fileEventLogger")
    private EventLogger fileEventLogger;


    @Bean
    public CombinedEventLogger combinedEventLogger() {

        List<EventLogger> loggers = new ArrayList<>();
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);

        CombinedEventLogger combinedEventLogger = new CombinedEventLogger();

        combinedEventLogger.setLoggers(loggers);
        return combinedEventLogger;
    }
}
