package se.kth.iv1350.model;

/**
 * Strategy interface for applying a discount to a sale.
 */
public interface DiscountStrategy {
    /**
     * Returns the discount rate based on customer ID and sale.
     *
     * @param customerID The ID of the customer.
     * @param sale The sale to apply the discount to.
     * @return The discount as a percentage (e.g. 0.1 for 10%).
     */
    float getDiscount(int customerID, SaleDTO sale);
}
