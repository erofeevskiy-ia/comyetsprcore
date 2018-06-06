package utills;

import java.io.*;

public class FileUtils {
    public static boolean writeStringToFile(String str, String filepath) {
        File file = new File(filepath);
        try (OutputStream os = new FileOutputStream(file, true);
             OutputStreamWriter osw = new OutputStreamWriter(os)) {
            osw.write(str + '\n');

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}