package se.kth.iv1350.model;

/**
 * Represents a payment made by a customer.
 */
public class Payment {
    private float amountPaid;

    /**
     * Creates a new instance of Payment.
     *
     * @param amountPaid The amount the customer paid.
     */
    public Payment(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return The amount paid by the customer.
     */
    public float getAmountPaid() {
        return amountPaid;
    }

    /**
     * Calculates the change to give back to the customer.
     *
     * @param totalPrice The total price of the sale.
     * @return The change to return to the customer.
     */
    public float calculateChange(float totalPrice) {
        return amountPaid - totalPrice;
    }
}
