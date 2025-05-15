package se.kth.iv1350.view;

import se.kth.iv1350.model.RevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Logs total revenue to a file every time a sale is completed.
 */
public class TotalRevenueFileOutput implements RevenueObserver {
    private static final String LOG_FILE_NAME = "revenue-log.txt";
    private PrintWriter logStream;
    private float totalRevenue;

    /**
     * Creates a new instance and opens the log file for writing.
     */
    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
        } catch (IOException e) {
            System.out.println("Could not open revenue log file.");
            e.printStackTrace();
        }
    }

    /**
     * Called when a sale is completed. Adds revenue and logs it to file.
     *
     * @param revenue The revenue from the latest sale.
     */
    @Override
    public void updateRevenue(float revenue) {
        totalRevenue += revenue;
        logRevenue();
    }

    private void logRevenue() {
        logStream.printf("[Total Revenue] %.2f SEK\n", totalRevenue);
    }
}
