package se.kth.iv1350.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Singleton class that logs system errors to a file.
 */
public class FileLogger {
    private static final String LOG_FILE_NAME = "error-log.txt";
    private static FileLogger instance;
    private PrintWriter logStream;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
        } catch (IOException e) {
            System.out.println("Could not open log file.");
            e.printStackTrace();
        }
    }

    /**
     * Returns the single instance of this class.
     *
     * @return The singleton instance.
     */
    public static FileLogger getInstance() {
        if (instance == null) {
            instance = new FileLogger();
        }
        return instance;
    }

    /**
     * Logs an exception with a timestamp and stack trace.
     *
     * @param exception The exception to log.
     */
    public void logException(Exception exception) {
        LocalDateTime timestamp = LocalDateTime.now();
        logStream.println("[" + timestamp + "] EXCEPTION: " + exception.getMessage());
        for (StackTraceElement element : exception.getStackTrace()) {
            logStream.println("\tat " + element);
        }
        logStream.println();
    }
}