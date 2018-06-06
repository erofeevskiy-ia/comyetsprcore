package app;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Component
@Scope(scopeName = "prototype")
public class Event {
    private static AtomicInteger AUTO_ID = new AtomicInteger(0);
    private int id;
    private String msg;
    private java.util.Date data;
    private DateFormat df;

    @Autowired
    public Event(Date data, DateFormat df) {
        id = AUTO_ID.getAndIncrement();
        this.data = data;
        this.df = df;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", data=" + df.format(data) +

                '}';
    }
}

