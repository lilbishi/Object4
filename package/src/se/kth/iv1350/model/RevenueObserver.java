package se.kth.iv1350.model;

/**
 * Observer interface for classes that want to be notified of total revenue updates.
 */
public interface RevenueObserver {
    /**
     * Called when a sale is completed.
     * @param revenue The revenue from the latest sale.
     */
    void updateRevenue(float revenue);
}

