package se.kth.iv1350.view;

import se.kth.iv1350.model.RevenueObserver;

/**
 * Displays the total revenue to the console every time a sale is completed.
 */
public class TotalRevenueView implements RevenueObserver {
    private float totalRevenue;

    /**
     * Called when a sale is completed. Adds the latest revenue to the total and displays it.
     *
     * @param revenue The revenue from the latest sale.
     */
    @Override
    public void updateRevenue(float revenue) {
        totalRevenue += revenue;
        displayRevenue();
    }

    /**
     * Prints the current total revenue to the console.
     */
    private void displayRevenue() {
        System.out.println("------------- Notification to all observers -------------");
        System.out.printf("Current total revenue is: %.2f kr\n", totalRevenue);
        System.out.println("----------------------------------------------------------");
    }
}
