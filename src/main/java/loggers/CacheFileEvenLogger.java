package loggers;

import app.Event;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import utills.FileUtils;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Data
@Component(value = "cacheFileEventLogger")
@DependsOn(value = "fileEventLogger")
public class CacheFileEvenLogger extends FileEventLogger {

    @Value(value = "${cacheSize}")
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEvenLogger() {
        cache = new ArrayList<>();
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if(cache.size()>=cacheSize){
            writeEventsFromCache("");
            cache.clear();
        }
    }

    @PreDestroy
    void destroy(){
//        if(!cache.isEmpty())
            writeEventsFromCache("Destroy");
    }

    private void writeEventsFromCache(String str) {
        cache.forEach(e->FileUtils.writeStringToFile(e.toString()+str,super.getFilename()));
    }

}
