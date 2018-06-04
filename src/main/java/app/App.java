package app;

import interfaces.EventLogger;
import lombok.Setter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Setter
public class App {
    private Client client;
    private EventLogger el;

    public void logEvent(Event event, String str) {

        String message = str.replaceAll(client.getId(), client.getFullname());
        event.setMsg(message);
        el.logEvent(event);

    }

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        App app = applicationContext.getBean(App.class);
        int count = 0;
        applicationContext.registerShutdownHook();
        while (true) {
            Event event = applicationContext.getBean(Event.class);
            count++;
            Thread.currentThread().sleep(200);
            app.logEvent(event, "Bla bla 2:");
            System.out.println(event.toString());
            if (count >= 7) {
                //throw new RuntimeException("kjdsgh");
                System.exit(1);
                //applicationContext.close();
            }
        }
    }
}
