package app;

import interfaces.EventLogger;

public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(Event event){
        System.out.println(event);
    }
}
