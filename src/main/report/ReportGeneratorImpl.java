package main.report;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by thimira on 06/05/17.
 */
public class ReportGeneratorImpl implements ReportGenerator {

    @Override
    public void record(String message) {
        // Create the log file name using timestamp value.
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String fileName = "src/main/logs/MessageRecorder" + timeStamp + ".log";
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(message);
        } catch (IOException e) {
            System.out.println("Can't generate Report !");
        }
    }
}
