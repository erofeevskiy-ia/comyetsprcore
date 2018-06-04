package loggers;

import app.Event;
import interfaces.EventLogger;
import lombok.Data;
import utills.FileUtils;

import java.io.File;

@Data
public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    //public FileEventLogger() {}
//    public FileEventLogger(String filename) {
//        this.filename = filename;
//    }

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
