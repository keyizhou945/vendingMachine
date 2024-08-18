package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName Logger
 * @Description
 * @Author Keyi Zhou
 * @Date 2024/4/11 15:29
 **/
public class Logger {
    private static PrintWriter dataOutput;

    public static void log(String type, Double amount,Double currentBalance) {
        String filePath = "Log.txt";
        File dateFile = new File(filePath);

        try {
            if (dataOutput == null) {
                dataOutput = new PrintWriter(new FileOutputStream(dateFile));
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:s a");
            String dateStr = LocalDateTime.now().format(formatter);

            String message = String.format("%-22s %-20s : $%6.2f     $%6.2f", dateStr, type, amount, currentBalance);
            dataOutput.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataOutput.flush();
        }
    }



}
