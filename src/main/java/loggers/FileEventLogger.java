package loggers;

import app.Event;
import interfaces.EventLogger;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import utills.FileUtils;

import javax.annotation.PostConstruct;
import java.io.File;

@Data
@Component(value = "fileEventLogger")
public class FileEventLogger implements EventLogger {
    @Value("${fileName}")
    private String filename;
    private File file;

    @PostConstruct
    void init() {
        this.file = new File(filename);
        if (!file.canWrite())
            throw new RuntimeException("Cannot write to file");
    }

    @Override
    public void logEvent(Event event) {
        FileUtils.writeStringToFile(event.toString(),filename);
    }
}
