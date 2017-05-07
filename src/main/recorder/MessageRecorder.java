package main.recorder;

import java.io.*;

/**
 * Created by thimira on 06/05/17.
 */
public class MessageRecorder implements Recorder{

    @Override
    public void record(String message) {
        try(FileWriter fw = new FileWriter("src/main/logs/MessageRecorder.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(message);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
