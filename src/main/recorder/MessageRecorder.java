package main.recorder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by thimira on 06/05/17.
 */
public class MessageRecorder implements Recorder {

    @Override
    public void record(String message) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String fileName = "src/main/logs/MessageRecorder" + timeStamp + ".log";
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(message);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
