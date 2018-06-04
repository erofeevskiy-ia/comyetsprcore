package loggers;

import app.Event;
import lombok.Data;
import utills.FileUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class CacheFileEvenLogger extends FileEventLogger {
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

    void destroy(){
//        if(!cache.isEmpty())
            writeEventsFromCache("Destroy");
    }

    private void writeEventsFromCache(String str) {
        cache.forEach(e->FileUtils.writeStringToFile(e.toString()+str,super.getFilename()));
    }

}
